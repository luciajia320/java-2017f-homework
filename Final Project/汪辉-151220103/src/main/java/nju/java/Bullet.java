package main.java.nju.java;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public class Bullet extends Player{
	private int rank;
	private int hurt;
	private boolean is_alive=true;
	private boolean Delete=false;
	public Bullet(int rank,int x, int y, Field field) {
		super(x, y, field);
		// TODO Auto-generated constructor stub
		this.rank=rank;
		URL loc;
		if(rank==4)
		{	loc = this.getClass().getClassLoader().getResource("resources/firebullet.png");
			hurt=10;
		}
		else if(rank==5) {
			loc = this.getClass().getClassLoader().getResource("resources/waterBullet.png");
			hurt=15;
		}
		else {
			loc = this.getClass().getClassLoader().getResource("resources/Bullet.png");
			hurt=5;
		}
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        Image smallImage = image.getScaledInstance(SPACE,SPACE,Image.SCALE_FAST);
        this.setImage(smallImage);
      //  this.field.Getfromxy(x, y).SetPosition(this);
	}
	public void die() {
		is_alive=false;
	}
	public boolean IfIs_alive() {
		return is_alive;
	}
	public int getHurt() {
		return this.hurt;
	}
	public int getRank() {
		return this.rank;
	}
	@Override
	public void move(int x, int y) {
        int nx = this.x() + x;
        int ny = this.y() + y;
        if((nx<0||nx>=this.GetField().getBoardWidth())||(ny<0||ny>=this.GetField().getBoardHeight())) {
        	is_alive=false;
        	//Thread.interrupted();
        }
        else {
        //this.field.Getfromxy(this.x(), this.y()).ResetPosition();
        this.setX(nx);
        this.setY(ny);
       // this.field.Getfromxy(nx, ny).SetPosition(this);
        }
    }
	@Override
	public void run() {
        while (!this.field.is_completed()) {
        	if(this.is_alive) {
        			this.move(SPACE, 0);
            try {
            	this.field.repaint();
                Thread.sleep(1000);
             // this.field.repaint();

            } catch (Exception e) {

            }
        	}
       }
    }
	@Override
    public void moveTo(int x,int y) {
		 if((x<0||x>this.GetField().getBoardWidth())||(y<0||y>this.GetField().getBoardHeight())) is_alive=false;
	        else {
	        this.setX(x);
	        this.setY(y);
	        }
    }

}
