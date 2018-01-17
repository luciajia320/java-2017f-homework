import java.awt.Image;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import java.io.IOException;

enum Direction {
    UP, DOWN, LEFT, RIGHT
}

enum Camp {
    JUSTICE, EVIL
}

public abstract class Creature extends Thing2D implements Runnable {
    private Field field;
    private Position position;
    private boolean alive;
    Direction dir;
    private Camp camp;
    private int priority;
    private int speed;

    public Field getField() {
        return field;
    }

    public Position getPosition() {
        return position;
    }

    public Creature(Position position, Field field) {
        super(position.getX(), position.getY());

        this.alive = true;
        this.field = field;

//        URL loc = this.getClass().getClassLoader().getResource("player.png");
//        ImageIcon iia = new ImageIcon(loc);
//        Image image = iia.getImage();
//        this.setImage(image);
    }

    public boolean isAlive() {
        return alive;
    }

    public void setDead() {
        alive = false;
    }

    public int getPriority() {
        return priority;
    }

    public Camp getCamp() {
        return camp;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setCamp(Camp camp) {
        this.camp = camp;
    }

    void setImage(String path) {
        URL loc = this.getClass().getClassLoader().getResource(path);
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }

    void setDeadImage(String path) {
        URL loc = this.getClass().getClassLoader().getResource(path);
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setDeadImage(image);
    }

    /*
    @Override
    public void record(FileWriter file) {
        super.record(this.field.file);
        try {
            if (alive)
                this.field.file.write(1 + " ");
            else
                this.field.file.write(0 + " ");
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
    */

    public Direction setDirection() {
        Random rand = new Random();
        int t = rand.nextInt(4);
        switch (t) {
            case 0:
                return Direction.LEFT;
            case 1:
                return Direction.RIGHT;
            case 2:
                return Direction.UP;
            case 3:
                return Direction.DOWN;
            default:
                return Direction.DOWN;
        }
    }

    public void move(Direction dir, int dx, int dy) {
        if (dir == Direction.UP) {
            if (y()-60<0) {
                return;
            }
            setY(y()-60);
        }
        else if (dir == Direction.DOWN) {
            if (y()+120 > field.getBoardHeight()) {
                return;
            }
            setY(y()-60);
        }
        else if(dir == Direction.LEFT) {
            if (x()-60 < 0) {
                this.dir = Direction.RIGHT;
                return;
            }
            this.setX(x()-60);
        }
        else if(dir == Direction.RIGHT) {
            if (x()+120 > field.getBoardWidth()) {
                this.dir = Direction.LEFT;
                return;
            }
            this.setX(x()+60);
        }

        ArrayList enemy;
        ArrayList friend;
        if (this.camp == Camp.JUSTICE) {
            enemy = this.field.getEvils();
            friend = this.field.getJustices();
        }
        else {
            enemy = this.field.getJustices();
            friend = this.field.getEvils();
        }
        for (int i = 0; i < enemy.size(); i++) {
            if (x() == ((Creature)enemy.get(i)).x() && y() == ((Creature)enemy.get(i)).y() && ((Creature)enemy.get(i)).alive) {
                setX(x());
                setY(y());
                break;
            }
        }

        for (int i = 0; i <friend.size(); i++) {
            if (x() == ((Creature)friend.get(i)).x() && y() == ((Creature)friend.get(i)).y() && ((Creature)friend.get(i)).alive) {
                setX(x());
                setY(y());
                break;
            }
        }
    }

    private void fight(Creature enemy) {
        Random rand = new Random();
        int p1 = this.priority;
        int p2 = enemy.priority;
        if (rand.nextInt(p1+p2) > p1) {
            this.setDead();
            this.setDeadImage("dead.png");
        }
        else {
            enemy.setDead();
            enemy.setDeadImage("dead.png");
        }
    }

    /*
    public void fightEnemies() {
        Creature[] enemies;
        if (this.camp == Camp.JUSTICE) {
            enemies = this.getField().getEvils();
        }
        else {
            enemies = this.getField().getJustices();
        }
        for (int i = 0; i < enemies.length; i++) {
            if (!enemies[i].isAlive())
                continue;
            else {
                if (Math.abs(enemies[i].getPosition().getX()-this.x()) <= 1 && Math.abs(enemies[i].getPosition().getY()-this.y()) <= 1) {
                    this.fight(enemies[i]);
                }
            }
        }
    }
    */

    public boolean collision(Creature c)
    {
        if((x() - c.x())>=-100&&(x() - c.x())<= 100 && (y() - c.y()) >= -100 && (y() - c.y()) <= 100)
            return true;
        else
            return false;
    }

    void update() {
        ArrayList enemy;
        ArrayList friend;
        if (this.camp == Camp.JUSTICE) {
            enemy = this.field.getEvils();
            friend = this.field.getJustices();
        }
        else {
            enemy = this.field.getJustices();
            friend = this.field.getEvils();
        }
        for(int i = 0; i < enemy.size(); i++)
        {
            Creature c = (Creature)enemy.get(i);
            if(c.isAlive()) {
                if (collision(c)) {
                    fight(c);
                    break;
                }
            }
        }
    }

    public void run() {
        while (!Thread.interrupted() && alive && !field.completed) {
            Random rand = new Random();

            synchronized (Creature.class) {
                move(setDirection(), 0, 0);
                update();
                this.field.updateState();
            }

            try {
                Thread.sleep(rand.nextInt(1000) + 1000);
                this.field.repaint();
            } catch (Exception e) {

            }
        }
    }
}