package main.java.nju.java;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.*;

public abstract class Creature extends Thing2D implements Runnable{
    private static final int OFFSET = 30;
    private static final int SPACE = 40;
    private static final String dirctoryPath = "src/records";
    private static final int sleeptime = 500;

    private volatile static boolean waitflag = false;
    private volatile static boolean shutdownRequested = false;
    private static Lock lock = new ReentrantLock();
    private static int stepCount = 0;

    private Position position;
    private boolean alivestatus;
    private int speed;
    private int strength;
    private int legion;
    private Field field;


    Creature(Field field, Position position){
        super(OFFSET+position.getX()*SPACE, OFFSET+position.getY()*SPACE);
        alivestatus = true;
        setPosition(position);
        this.field = field;
    }


    public static void creatureWait() {waitflag = true;}

    public static void creatureWakeup() { waitflag = false;}

    public static void resetArgument(){
        shutdownRequested = false;
        lock = new ReentrantLock();
        stepCount = 0;
    }

    public static int getStepCount(){ return stepCount; }

    public void setPosition(Position position){
        this.position = position;
        position.setHolder(this);
    }
    public Position getPosition(){
        return position;
    }

    public boolean isAlive(){ return alivestatus; }
    public void dead() { alivestatus = false; }

    public Field getField(){ return field; }
    public void setSpeed(int speed){this.speed = speed;}

    public void setStrength(int strength) {this.strength = strength; }
    public int getStrength()   {return strength;}

    public void setLegion(int legion) {this.legion = legion;}
    public int getLegion()  {return legion;}

    public int getSpeed()   { return speed;}

    void setImage(String imagePath){
        URL loc = this.getClass().getClassLoader().getResource(imagePath);
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }

    public void move(int offsetX, int offsetY){
        int nx = this.imageX() + offsetX*SPACE;
        int ny = this.imageY() + offsetY*SPACE;
        this.setImageX(nx);
        this.setImageY(ny);
    }

    public boolean fight(Creature creature){
        Random r1 = new Random();
        Random r2 = new Random();

        int power1 = r1.nextInt(this.getStrength());
        int power2 = r2.nextInt(creature.getStrength());
        /*使得两个生物必然分出胜负*/
        while(power1 == power2){
            power1 = r1.nextInt(this.getStrength());
            power2 = r2.nextInt(creature.getStrength());
        }
        if(power1 > power2) return true;
        else return false;
    }

    public abstract boolean hasEnemy();

    public abstract void fightEnemy();

    public abstract int currentAliveNumber();

    public abstract int findAliveEnemy();   //返回值为找到的第一个活着敌人的下标

    public void run() {
        while (!Thread.interrupted() && !shutdownRequested && this.isAlive()) {
            try{
                Random random = new Random();
                boolean control = true;
                do {
                    int[] delta = getRandomDelda(random.nextInt(1000));
                    int deltaX = delta[0];
                    int deltaY = delta[1];

                    int posX = position.getX() + deltaX;
                    int posY = position.getY() + deltaY;

                    synchronized (field) {
                        while(waitflag) wait();
                        if(!hasEnemy()){ shutdownRequested = true;
                            this.field.record(this.dirctoryPath);
                            ++stepCount;
                            this.field.repaint(); break;
                        }
                        if(!this.isAlive()) break; //执行之前可能已经被杀
                        fightEnemy();
                        if(!this.isAlive() || !hasEnemy()) break;

                        if(field.isCrossBorder(posX, posY)) continue;
                        /*位置上已经有生物,重新计算位置*/
                        if (field.getPositionAt(posX, posY).isOccupied()) continue;
                        else {
                            position.clear();
                            setPosition(field.getPositionAt(posX, posY));
                            this.move(deltaX , deltaY);
                            control = false;
                        }
                        this.field.record(this.dirctoryPath);
                        ++stepCount;
                    }
                }while(control);

                this.field.repaint();
                Thread.sleep(this.sleeptime);
            } catch (Exception e) { e.printStackTrace();}
        }
    }

    private int[] getRandomDelda(int sand){
        int[] delta = new int[2];
        Random rand = new Random(sand);
        delta[0] = (rand.nextInt(speed)+1)*(rand.nextInt(3) - 1);
        delta[1] = (rand.nextInt(speed)+1)*(rand.nextInt(3) - 1);
        if(currentAliveNumber() <= 2){
            int idx = findAliveEnemy();
            if(idx != -1){
                Creature[] creatures;
                if(this instanceof Huluwa || this instanceof Laoyeye) creatures = field.getYaoguais();
                else creatures = field.getHuluwas();
                int offsetX = creatures[idx].getPosition().getX() - this.getPosition().getX();
                int offsetY = creatures[idx].getPosition().getY()-this.getPosition().getY();

                int absX = Math.abs(offsetX), absY = Math.abs(offsetY);
                if(offsetX == 0) absX = 1;
                if(offsetY == 0) absY = 1;
                delta[0] = (rand.nextInt(speed)+1)*(offsetX/absX);
                delta[1] = (rand.nextInt(speed)+1)*(offsetY/absY);
            }
        }
        return delta;
    }
}
