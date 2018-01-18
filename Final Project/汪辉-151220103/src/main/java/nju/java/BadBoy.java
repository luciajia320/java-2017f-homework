package main.java.nju.java;

import java.awt.Image;
import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;

public class BadBoy extends Player{
	private int blood=10;
	private int speed=1;
	private int rank=9;
	//private int hurt=5;
	private static boolean is_good=true;
	private boolean is_alive=true;
	public BadBoy(int rank,int blood,int speed,int x,int y,Field field) {
		super(x, y, field);
		// TODO Auto-generated constructor stub
		this.rank=rank;
		this.blood=blood;
		this.speed=speed;
		URL loc;
		if(rank==8)
			loc = this.getClass().getClassLoader().getResource("resources/"+rank+".jpg");
		else loc = this.getClass().getClassLoader().getResource("resources/"+rank+".png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        Image smallImage = image.getScaledInstance(SPACE,SPACE,Image.SCALE_FAST);
        this.setImage(smallImage);
        //this.field.Getfromxy(x, y).SetPosition(this);
	}
	public int GetRank() {
		return rank;
	}
	public void SetSpeed(int num) {
		this.speed=num;
	}
	public int getSpeed() {
		return this.speed;
	}
	public int getBlood() {
		return this.blood;
	}
	public int getHurt() {
		return this.blood;
	}
	public void Hurt(int num) {
		if(num>=this.blood)
			this.is_alive=false;
		else this.blood-=num;
	}
	public boolean IfIs_alive() {
		return is_alive;
	}
	public boolean IfIs_good() {
		return is_good;
	}
	@Override
	public void move(int x, int y) {
        int nx = this.x() + x;
        int ny = this.y() + y;
        if(nx<=0) {
        	this.field.Setcompleted();
        	return;
        }
        else 
        	nx=nx%this.field.getBoardWidth();
        if(ny<0)  ny=this.field.getBoardHeight()+ny;
        else	ny=ny%this.field.getBoardHeight();
        this.field.Getfromxy(this.x(), this.y()).ResetPosition();
        this.setX(nx);
        this.setY(ny);
        this.field.Getfromxy(nx, ny).SetPosition(this);
    }
	@Override
	public void run() {
        while (!this.field.is_completed()) {
           // Random rand = new Random();
        	if(this.is_alive) {
            this.move(-SPACE,0);
        	}
            try {
            	   this.GetField().repaint();
                Thread.sleep(3000);

            } catch (Exception e) {

            }
        }
    }
}
