package nju.java;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public abstract class Creature extends Thing2D{

    protected Field field;

    protected int attack = 0;
    protected int speed = 10;
    protected boolean isAlive = true;
    protected Image dead_image;


    public Creature(int x, int y, Field f){
        super(x, y);
        this.field = f;
    }

    public abstract ArrayList< ? extends Creature>  getEnemies();

    public abstract String getName();

    public int getAttack(){
        return attack;
    }

    public void setAttack(int num){
        this.attack = num;
    }

    //basic move
    public void move(int x, int y) {
        try{
            int nx = this.x() + x;
            int ny = this.y() + y;

            if ( nx > field.getBoardWidth() - Field.SPACE) nx = field.getBoardWidth() - Field.SPACE;
            else if ( nx < 0) nx = 0;

            if ( ny > field.getBoardHeight() - Field.SPACE) ny = field.getBoardHeight() - Field.SPACE;
            else if ( ny < 0) ny = 0;

            this.setX(nx);
            this.setY(ny);

            Thread.sleep(100);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        field.repaint();
    }

    //speed controlled move
    public void moveControlled(int x, int y){

        int nx = this.x() + x;
        int ny = this.y() + y;
        if ( nx > field.getBoardWidth() - Field.SPACE) x = field.getBoardWidth() - Field.SPACE - this.x();
        else if ( nx < 0) x = 0 - this.x();
        if ( ny > field.getBoardHeight() - Field.SPACE) ny = field.getBoardHeight() - Field.SPACE - this.y();
        else if ( ny < 0) y = 0 - this.y();

        int x_step = Math.abs(x/speed);
        int y_step = Math.abs(y/speed);
        int x_count = 0;
        int y_count = 0;
        int x_direction = 1;
        int y_direction = 1;
        if ( x < 0 ) x_direction = -1;
        if ( y < 0 ) y_direction = -1;

        while ( x_count < x_step || y_count < y_step){
            if ( x_count < x_step && y_count < y_step){
                move(x_direction * speed, y_direction * speed);
                x_count++;
                y_count++;
            }
            else if (x_count < x_step ){
                move(x_direction * speed, 0);
                x_count++;
            }
            else if (y_count < y_step ){
                move(0, y_direction * speed);
                y_count++;
            }
        }

    }

    //move towards a target
    public void moveTo(Creature target){

        int x_move = target.x() - this.x();
        int y_move = target.y() - this.y();

        if (Math.abs(x_move) > Math.abs(y_move)){
            if ( x_move > 0) x_move -= Field.SPACE;
            else x_move += Field.SPACE;
        }
        else{
            if ( y_move > 0) y_move -= Field.SPACE;
            else y_move += Field.SPACE;
        }

        this.moveControlled(x_move, y_move);

    }

    public boolean isAlive(){
        return isAlive;
    }

    public Creature FindTarget(){
        ArrayList<? extends Creature> enemies = this.getEnemies();
        int min_d = 99999;
        Creature target = null;
        if (!enemies.isEmpty()) {
            for (Creature c : enemies) {
                if ( c.isAlive() ) {
                    int d = Math.abs(c.x() - this.x()) + Math.abs(c.y() - this.y());
                    if (d < min_d) {
                        target = c;
                        min_d = d;
                    }
                }
            }
        }
        return target;
    }

    public synchronized boolean Battle(Creature another) {
        synchronized (another){
            Random rand = new Random();
            this.attack = this.attack + rand.nextInt(2);
            another.setAttack(another.getAttack() + rand.nextInt(2));
            if ((this.attack) >= (another.getAttack())) {
                another.die();
                another.notifyAll();
                notifyAll();
                return true;
            } else {
                this.die();
                another.notifyAll();
                notifyAll();
                return false;
            }
        }
    }

    public boolean withinRange( Creature target ){
        int x_range = Math.abs( target.x() - this.x() );
        int y_range = Math.abs( target.y() - this.y() );
        return ( x_range + y_range < Field.SPACE * 1.2 );
    }


    public void die(){
        this.isAlive = false;
        this.setImage(this.dead_image);
        Thread.interrupted();
    }

    public void setDead_image(String dead_pic_name){
        URL loc = this.getClass().getClassLoader().getResource(dead_pic_name);
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        //统一图片大小为100*100
        this.dead_image =image.getScaledInstance(field.SPACE, field.SPACE, Image.SCALE_SMOOTH);
    }

}
