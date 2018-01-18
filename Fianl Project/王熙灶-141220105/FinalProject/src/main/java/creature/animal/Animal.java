package creature.animal;

import creature.Creature;
import static util.Constant.*;

import space.Position;
import util.Direction;
import util.GroundState;

import java.awt.Image;
import java.util.*;

import static util.Direction.*;

public class Animal extends Creature {
    private volatile util.State currentState = util.State.WAIT;
    Image imageAlive;
    Image imageDead;
    volatile String imageAlivePath;
    volatile String imageDeadPath;
    private List<Animal> enemyList;
    private Random random = new Random();
    private int cheerTime = 50;
    public static volatile boolean runnable = false;

    public void resetCheerTime() {
        cheerTime = 50;
    }

    public void setEnemyList(List<Animal> enemies) {
        enemyList = enemies;
    }

    public util.State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(util.State currentState) {
        this.currentState = currentState;
    }

    public boolean isDead() {
        return (currentState == util.State.DEAD);
    }

    @Override
    public Image getImage() {
        if(isDead()) {
            return imageDead;
        }
        else {
            return imageAlive;
        }
    }

    @Override
    public void run() {
        while (runnable) {
            if(isDead()) return;

            if((this instanceof GrandPa || this instanceof SnakeEssence)  && cheerTime > 0) {
                currentState = util.State.CHEER;
                cheerTime--;
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    break;
                }
                continue;
            }

            currentState = util.State.FIGHT;

            if(random.nextInt(2) == 0)
                doMove();
            else
                doAttack();

            try {
                Thread.sleep(500);
                recorder.add();

                ground.repaint();
                status.repaint();

                if(calaCrops.Win() || essenceCrops.Win()) {
                    runnable = false;
                    ground.setState(GroundState.OVER);
                    control_stop.setEnabled(false);
                    ground.repaint();
                    status.repaint();
                    return;
                }
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public boolean movable(Direction d) {
        int x = getPosition().getX();
        int y = getPosition().getY();

        switch (d) {
            case UP: return y != 0 && space.getPos(x, y-1).getHolder() == null;
            case DOWN: return (y != space.getHeight()-1) && space.getPos(x, y+1).getHolder() == null;
            case LEFT: return x != 0 && space.getPos(x-1, y).getHolder() == null;
            case RIGHT: return (x != space.getWidth()-1) && space.getPos(x+1, y).getHolder() == null;
            case UPLEFT: return y != 0 && x != 0 && space.getPos(x-1, y-1).getHolder() == null;
            case UPRIGHT: return y != 0 && (x != space.getWidth()-1) && space.getPos(x+1, y-1).getHolder() == null;
            case DOWNLEFT: return (y != space.getHeight()-1) && x != 0 && space.getPos(x-1, y+1).getHolder() == null;
            case DOWNRIGHT: return (y != space.getHeight()-1) && (x != space.getWidth()-1) && space.getPos(x+1, y+1).getHolder() == null;
            default: return false;
        }
    }

    public void move(Direction direction) {
        int x = getPosition().getX();
        int y = getPosition().getY();

        switch (direction) {
            case UP: y--; break;
            case DOWN: y++; break;
            case LEFT: x--; break;
            case RIGHT: x++; break;
        }
        space.bind(this, x, y);
    }

    public void doMove() {
        synchronized (space) {
            Animal enemy = getRandomEnemy();
            if(enemy == null) {
                System.out.println("GetRandomEnemy get null enemy!");
                return;
            }
            Direction x = (enemy.getPosition().getX() > getPosition().getX()) ? RIGHT : LEFT;
            Direction y = (enemy.getPosition().getY() > getPosition().getY()) ? DOWN : UP;

            List<Direction> EnemyDrivedMovableDirection = new ArrayList<>();
            if(movable(x)) EnemyDrivedMovableDirection.add(x);
            if(movable(y)) EnemyDrivedMovableDirection.add(y);

            List<Direction> movableDirection = new ArrayList<>();
            for(Direction direction: Direction.values()) {
                if(movable(direction)) movableDirection.add(direction);
            }

            if(EnemyDrivedMovableDirection.size() > 0) {
                Direction d = EnemyDrivedMovableDirection.get(random.nextInt(EnemyDrivedMovableDirection.size()));
                move(d);
            }
            else if(movableDirection.size() > 0) {
                Direction d = movableDirection.get(random.nextInt(movableDirection.size()));
                move(d);
            }
        }
    }

    @Deprecated
    public void addEnemy(Animal animal) {
        enemyList.add(animal);
    }

    @Deprecated
    public void addEnemy(List<? extends Animal> la) {
        enemyList.addAll(la);
    }

    @Deprecated
    public void delEnemy(Animal animal) {
        enemyList.remove(animal);
    }

    public boolean meetEnemy(Animal a) {
        return enemyList.contains(a) && distance(a) <= 3 && !a.isDead();
    }

    public void doAttack() {
        for(Animal enemy: enemyList) {
            if(meetEnemy(enemy)) {
                if(random.nextInt(100) < 50) {
                    enemy.setCurrentState(util.State.DEAD);
                }
                else {
                    this.setCurrentState(util.State.DEAD);
                }
            }
        }
    }

    /**
     * @return 返回距离当前生物体最近并且还存活的敌人
     */
    @Deprecated
    public Animal findNearestEnemy() {
        int min = Integer.MAX_VALUE;
        Animal res = null;
        for (Animal a: enemyList) {
            if(a.isDead()) {
                int dis = distance(a);
                if(dis < min) {
                    min = dis;
                    res = a;
                }
            }
            else {
                delEnemy(a);
            }
        }
        return res;
    }

    public Animal getRandomEnemy() {
        return enemyList.get(random.nextInt(enemyList.size()));
    }

    public static void main(String[] args) {
        List<Animal> bads = new ArrayList<>(Arrays.asList(
                scorpion, snake, minionA, minionB,
                minionC, minionD, minionE, minionF
        ));

        List<Animal> goods = new ArrayList<>(Arrays.asList(
                grandpa, calaA, calaB, calaC,
                calaD, calaE, calaF, calaG
        ));

        for(Animal a: bads) {
            a.addEnemy(goods);
        }

        for(Animal a: goods) {
            a.addEnemy(bads);
        }

        scorpion.setPosition(new Position<>(7, 4));
        snake.setPosition(new Position<>(7, 1));
        minionA.setPosition(new Position<>(8, 3));
        minionB.setPosition(new Position<>(8, 4));
        minionC.setPosition(new Position<>(8, 5));
        minionD.setPosition(new Position<>(9, 4));
        minionE.setPosition(new Position<>(10, 4));
        minionF.setPosition(new Position<>(11, 4));

        grandpa.setPosition(new Position<>(3, 1));
        calaA.setPosition(new Position<>(3, 4));
        calaB.setPosition(new Position<>(2, 3));
        calaC.setPosition(new Position<>(2, 5));
        calaD.setPosition(new Position<>(1, 2));
        calaE.setPosition(new Position<>(1, 6));
        calaF.setPosition(new Position<>(0, 1));
        calaG.setPosition(new Position<>(0, 7));

        for(Animal a: bads) {
            System.out.println(a + ": " + a.findNearestEnemy());
        }

        for(Animal a: goods) {
            System.out.println(a + ": " + a.findNearestEnemy());
        }
    }
}