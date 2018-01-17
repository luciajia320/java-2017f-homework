package main.java.nju.java;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public class HuluBro extends Player{
	private int rank=0;
	private int Blood=5;
	private int speed=1;
	//private int hurt=2;
	private static boolean is_good=true;
	private boolean is_alive=true;
	public HuluBro(int rank,int blood,int speed,int x,int y,Field field) {
		super(x, y, field);
		this.rank=rank;
		this.Blood=blood;
		this.speed=speed;
		URL loc = this.getClass().getClassLoader().getResource("resources/"+rank+".png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();

Image smallImage = image.getScaledInstance(SPACE,SPACE,Image.SCALE_FAST);
        this.setImage(smallImage);
       // this.field.Getfromxy(x, y).SetPosition(this);
	}
	public void SetSpeed(int num) {
		this.speed=num;
	}
	public int getSpeed() {
		return this.speed;
	}
	public int getHurt() {
		return this.Blood;
	}
	public int getBlood() {
		return this.Blood;
	}
	public void Hurt(int num) {
		if(num>=this.Blood)
			this.is_alive=false;
		else this.Blood-=num;
	}
	public boolean IfIs_alive() {
		return is_alive;
	}
	public boolean IfIs_good() {
		return is_good;
	}
	public int getRank() {
		return rank;
	}
	@Override
	public void run() {
        while (!this.field.is_completed()) {
        	//Position p=this.field.Getfromxy(this.x()+SPACE, this.y());
    		//if(p.is_hasThing()&&!p.is_goodboy())
    		//	this.Hurt(p.getHurt());
        	if(this.is_alive) {	
            this.field.AddBullet(new Bullet(this.rank,this.x()+SPACE,this.y(),this.field));
            try {
            	this.field.repaint();
                Thread.sleep(2000);
             // this.field.repaint();

            } catch (Exception e) {

            }
        	}
        }
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
