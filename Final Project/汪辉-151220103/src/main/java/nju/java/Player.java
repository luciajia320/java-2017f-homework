package main.java.nju.java;

import java.awt.Image;
import java.net.URL;
import java.util.Random;
import javax.swing.ImageIcon;

public class Player extends Thing2D implements Runnable {
    protected Field field;

    public Player(int x, int y, Field field) {
        super(x, y);

        this.field = field;

        URL loc = this.getClass().getClassLoader().getResource("resources/player.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
       // this.field.Getfromxy(x, y).SetPosition(this);
    }
    public Field GetField() {
    	return this.field;
    }
    public void move(int x, int y) {
        int nx = this.x() + x;
        int ny = this.y() + y;
        if(nx<0) nx=this.field.getBoardWidth()+nx;
        else 
        	nx=nx%this.field.getBoardWidth();
        if(ny<0)  ny=this.field.getBoardHeight()+ny;
        else	ny=ny%this.field.getBoardHeight();
        this.field.Getfromxy(this.x(), this.y()).ResetPosition();
        this.setX(nx);
        this.setY(ny);
        this.field.Getfromxy(nx, ny).SetPosition(this);
    }
    public void moveTo(int x,int y) {
    	 if(x<0) x=this.field.getBoardWidth()+x;
         else 
         	x=x%this.field.getBoardWidth();
         if(y<0)  y=this.field.getBoardHeight()+y;
         else	y=y%this.field.getBoardHeight();
    	this.setX(x);
    	this.setY(y);
    }
    public void run() {
        while (!Thread.interrupted()) {

            this.move(SPACE,0);
            try {
            	   this.field.repaint();
                Thread.sleep(15);
             // this.field.repaint();

            } catch (Exception e) {

            }
        }
    }
}