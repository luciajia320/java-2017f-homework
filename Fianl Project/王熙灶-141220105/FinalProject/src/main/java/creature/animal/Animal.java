package creature.animal;

import creature.Creature;
import static util.Constant.*;
import util.Direction;

import java.awt.Image;
import java.util.LinkedList;
import java.util.List;

public class Animal extends Creature implements Runnable {
    private State state = State.对阵;
    Image imageAlive;
    Image imageDead;
    List<Animal> enemyList = new LinkedList<>();

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean isAlive() {
        return (state != State.阵亡);
    }

    @Override
    public Image getImage() {
        if(isAlive()) {
            return imageAlive;
        }
        else {
            return imageDead;
        }
    }

    @Override
    public void run() {
        synchronized (space) {
            while (isAlive()) {
                Animal enemy = findNearestEnemy();
//                if(enemy == null) return;
                Direction x = (enemy.getPosition().getX() > getPosition().getX()) ? Direction.RIGHT : Direction.LEFT;
                Direction y = (enemy.getPosition().getY() > getPosition().getY()) ? Direction.DOWN : Direction.UP;
                if(movable(x)) {
                    move(x);
                } else if(movable(y)) {
                    move(y);
                }
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
        this.unbindWith();
        space.bind(this, x, y);
    }

    public void addEnemy(Animal animal) {
        enemyList.add(animal);
    }

    public void addEnemy(List<? extends Animal> la) {
        enemyList.addAll(la);
    }

    public void delEnemy(Animal animal) {
        enemyList.remove(animal);
    }

    public boolean meetEnemy(Animal a) {
        return enemyList.contains(a) && distance(a) == 1 && a.isAlive();
    }

    /**
     * @return 返回距离当前生物体最近并且还存活的敌人
     */
    public Animal findNearestEnemy() {
        int min = Integer.MAX_VALUE;
        Animal res = null;
        for (Animal a: enemyList) {
            if(a.isAlive()) {
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
}