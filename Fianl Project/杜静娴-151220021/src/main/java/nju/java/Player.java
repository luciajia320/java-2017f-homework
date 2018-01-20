package nju.java;


import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;

import static java.lang.Thread.yield;

public class Player extends Thing2D implements Runnable {
    protected String path = "player.png";
    protected Field field;

    private ArrayList<State> route = new ArrayList<State>();

    protected int full_life;
    protected int life;

    protected boolean dir = true;

    protected boolean moved = false;
    protected boolean busy = false;
    private boolean alive = true;


    private boolean team = true;
    private boolean stopped = false;


    private Player target;
    private Position battle_pos;

    public ArrayList<State> getRoute() {
        return route;
    }

    public void recover(){
        life = full_life;
        busy = false;
        stopped = false;
        alive = true;
        StringBuilder sb = new StringBuilder(path);
        if(sb.charAt(path.lastIndexOf('/')+1) == 'd') {
            sb.deleteCharAt(path.lastIndexOf('/') + 1);
            path = sb.toString();
            URL loc = this.getClass().getClassLoader().getResource(path);
            System.out.println(path);
            ImageIcon iia = new ImageIcon(loc);
            Image image = iia.getImage();
            this.setImage(image);
        }
    }
    public void setMoved(boolean moved) {
        this.moved = moved;
        if(moved)
            route.add(new State(new Position(x(),y()),life));
    }

    public void setTeam(boolean team) {
        this.team = team;
    }

    public boolean isTeam() {
        return team;
    }

    public boolean isMoved() {
        return moved;
    }

    public boolean isStopped() {
        return stopped;
    }

    public Player getTarget() {
        return target;
    }

    public int getFullLife() {
        return full_life;
    }

    public int getLife() {
        return life;
    }

    public boolean isBusy() {
        return busy;
    }


    public boolean isAlive() {
        return alive;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public ArrayList<String> getLog() {
        ArrayList<String> list = new ArrayList<String>();
        for(State s : route) {
            list.add(this+" "+s.toString());
        }
        return list;
    }
    public void reloadPath() {
        URL loc = this.getClass().getClassLoader().getResource(path);
        System.out.println(path);
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }

    public void die(){
        life = 0;
        alive = false;
        reloadPath();
        System.out.println(this+"阵亡！");
    }

    public void stop(){stopped = true;}

    public Player(int x, int y, Field field) {
        super(x, y);
//        stop();
        this.field = field;
        full_life = 1000;
        life = 1000;
        URL loc = this.getClass().getClassLoader().getResource(path);
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
        route.add(new State(new Position(x,y),life));
    }

    public void move(int x, int y) {
        int nx = this.x() + x;
        int ny = this.y() + y;
        this.setX(nx);
        this.setY(ny);
    }

    public void stopToFight(Player t){
        target = t;
        busy = true;
        battle_pos = new Position(x(),y());
    }

    public synchronized String hurt(int level){
        int hurt = 0;
        switch(level){
            case 2: hurt = 20;break;
            case 3: hurt = 40;break;
            case 4: hurt = 60;break;
            case 5: hurt = 80;break;
            case 1:
            default: hurt = 10;
        }
        life -= hurt;
        if(life <= 0){
            this.die();
        }
        return this+" -"+hurt+"!";
    }

    public Position getPos(){return new Position(this.x(),this.y());}

    public void randMove(){
        Random rand = new Random();
        if(!isBusy()) {
            if(dir)
                this.move(rand.nextInt(5)-1, 2 - rand.nextInt(5));
            else
                this.move(-rand.nextInt(5)+1, 2 - rand.nextInt(5));
            if(field.outOfLeftRange(this.getPos())){
                dir = true;
            }
            else if(field.outOfRightRange(this.getPos()))
                dir = false;
            if(this.getPos().getY()<=0) this.move(0,6);
            if(this.getPos().getY()>=field.getHeight()-60) this.move(0,-7);
        }
        else {
            if(dir)
                this.move(2, 2-rand.nextInt(5));
            else
                this.move(-2,2-rand.nextInt(5));
            if(field.outOfLeftRange(this.getPos())){
                dir = true;
            }
            else
                dir = false;
            if(Math.abs(x()-battle_pos.getX())>=10 || Math.abs(y()-battle_pos.getY())>=10)
                this.move(battle_pos.getX()-x(),battle_pos.getY()-y());
            dir = !dir;
        }
    }
    public void run() {
        stopped = false;
        while (!Thread.interrupted()) {
            if(stopped)
                return;
            try {
                field.mutex.waitForBuffing();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.println("Wax On! ");
            if(!isAlive() || stopped) {
                Thread.currentThread().interrupt();
                field.mutex.waxed();
                return;
            }
            if(life/full_life < 0.1)
                this.hurt(1);
            Random rand = new Random();
            if(!isBusy()) {
                try {
                    yield();
                    Thread.sleep(4 + rand.nextInt(2));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                double force = rand.nextGaussian();
                String result = this+" vs "+target+": ";
                if(!getTarget().isAlive())
                    this.busy = false;
                else
                    result += getTarget().hurt(((int) Math.ceil(force * 5)));
                field.takeNote(result);
                try{
                    long time = 200+100*(long)rand.nextGaussian();
                    while(time <= 0) time = 200+100*(long)rand.nextGaussian();
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            field.mutex.waxed();
        }
    }

    public void setLife(int life) {
        this.life = life;
    }
}