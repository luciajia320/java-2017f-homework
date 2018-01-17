package entity;

import MyException.OutOfBounce;
import ui.*;

import common.Constant;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

abstract public class Creature extends Thread implements Constant {
    public static Random random = new Random(SEED);
    public static int count = 0;
    // 获得全局, 拿到画笔实现JPanel的repaint()方法
    public static Ground ground;

    public Creature() {
        location = new Location();
        live = true; id = count++;
        try {
            setImg();
        } catch (OutOfBounce e) {
            System.out.println(e.getMessage());
        }
    }

    public int getX() { return location.getX(); }
    public int getY() { return location.getY(); }
    public void setPoint(int x, int y) {
        location.setLocation(x, y);
        ground.content[getY()][getX()] = id;
    }
    public void setEnemy(ArrayList<Creature> enemy) { this.enemy = enemy; }

    public int distance(Creature c) {
        int deltaX = getX() - c.getX();
        int deltaY = getY() - c.getY();
        return (deltaX * deltaX) + (deltaY * deltaY);
    }

    // 是否遇到敌方, 判断标准为距离<=1
    public boolean conflict(Creature c) {
        if (c.live == true && distance(c) <= 1) return true;
        return false;
    }

    public void battle(Creature c) {
        int x = random.nextInt();

        // 死亡的一方所在位置ground.content设为id的负值
        if (x < SEED / 2) {   // 我方死亡
            live = false;
            ground.content[getY()][getX()] = -id;
            try {
                setImg();
            } catch (OutOfBounce e) {
                System.out.println(e.getMessage());
            }
        } else {        // 敌方死亡
            c.live = false;
            ground.content[c.getY()][c.getX()] = -c.id;
            try {
                c.setImg();
            } catch (OutOfBounce e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // 目标位置是否是可移动的, 超边界不可移动, 若位置上原角色已死亡可移动
    private boolean movable(int x, int y) {
        if (x >= GROUNDWIDTH) return false;
        if (y >= GROUNDHEIGHT) return false;
        synchronized (ground.content) { if (ground.content[y][x] <= -1) return true; }
        return false;
    }

    @Override
    public void run() {
        while (runnable == true) {
            ground.save();
            move();
            ground.repaint();
            try {
                sleep(500);
            } catch (InterruptedException e) {}
        }

    }

    protected void move() {
        if (live == true) {
            // 如果产生冲突, 战争
            boolean isConflict = false;
            for (Creature e : enemy)
                // 锁定当前交锋对象
                synchronized (e) {
                    if (e.live == true && conflict(e) == true) {
                        battle(e);
                        isConflict = true;
                        break;
                    }
                }
            if (isConflict == true) return;

            // 找下一个攻击目标, 标准为距离最近
            int min = MAXDIS, k;
            Creature goal = null;
            // 锁定对象的enemy列表
            synchronized (enemy) {
                for (Creature e : enemy)
                    if (e.live == true && (k = distance(e)) <= min) {
                        goal = e;
                        min = k;
                    }
            }

            // 增加runnable判断和同步是为了防止多个线程同时作用, 多线都程运行到stopAll()之后
            if (goal == null && runnable == true) synchronized (ground.getState()) {  // 敌人全部死亡
                ground.setState(STATE.OVER);
                this.interrupt();
                return;
//                ground.gameOver(this);
            }

            // 如果敌方仍旧存在, 那么找距离最小的那个移动, 更新ground.content
            synchronized (ground.content) {
                if (goal.getX() < getX() && movable(getX() - 1, getY())) {
                    ground.content[getY()][getX()] = -1;
                    setPoint(getX() - 1, getY());
                } else if (goal.getX() > getX() && movable(getX() + 1, getY())) {
                    ground.content[getY()][getX()] = -1;
                    setPoint(getX() + 1, getY());
                } else if (goal.getY() < getY() && movable(getX(), getY() - 1)) {
                    ground.content[getY()][getX()] = -1;
                    setPoint(getX(), getY() - 1);
                } else if (movable(getX(), getY() + 1)) {
                    ground.content[getY()][getX()] = -1;
                    setPoint(getX(), getY() + 1);
                }
            }
        }
    }

    // 抽象方法, 不同角色有不同的图像
    abstract protected void setImg() throws OutOfBounce;

    // 取决于全局状态, runnable为true时表示在活动, 场景需要重画
    // 为false时表示结束游戏或者中断游戏, 暂停重画
    public boolean runnable = false;
    // grandpa: 0, huluwa: 1-7, snake: 8, scorpion: 9, monster: 10-?
    public int id;
    // 角色坐标
    protected Location location;
    // 角色图标
    protected Image img;
    // 是否存活
    protected boolean live;
    // 葫芦娃的敌人是monster, 反之...
    protected ArrayList<Creature> enemy;
}

class Huluwa extends Creature {
    @Override
    protected void setImg() throws OutOfBounce {
        if (id >= 8) throw new OutOfBounce("There is only 7 Huluwa!");
        else {
            if (live == true) this.img = Images.getImage(IMGPATH + id + ".png");
            else this.img = Images.getImage(IMGPATH + id + "_black.png");
        }
    }
}

class Grandpa extends Creature {
    Grandpa(int x, int y) {
        super(); setPoint(x, y);
    }

    @Override
    protected void setImg() {
        if (live == true) this.img = Images.getImage(IMGPATH + "grandpa.png");
        else this.img = Images.getImage(IMGPATH + "grandpa_black.png");
    }

    @Override
    public void run() {}

    @Override
    protected synchronized void move() {}
}

class Snake extends Creature {
    Snake(int x, int y) {
        super(); setPoint(x, y);
    }

    @Override
    protected void setImg() {
        if (live == true) this.img = Images.getImage(IMGPATH + "snake.png");
        else this.img = Images.getImage(IMGPATH + "snake_black.png");
    }

    @Override
    public void run() {}

    @Override
    protected synchronized void move() {}
}

class Scorpion extends Creature {
    Scorpion(int x, int y) {
        super(); setPoint(x, y);
    }

    @Override
    protected void setImg() {
        if (live == true) this.img = Images.getImage(IMGPATH + "scorpion.png");
        else this.img = Images.getImage(IMGPATH + "scorpion_black.png");
    }

    @Override
    public void run() {}

    @Override
    protected synchronized void move() {}
}

class Monster extends Creature {
    @Override
    protected void setImg() {
        if (live == true) this.img = Images.getImage(IMGPATH + "monster.png");
        else this.img = Images.getImage(IMGPATH + "monster_black.png");
    }
}