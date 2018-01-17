//package huluwa_final;

import java.awt.Image;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.Random;
import javax.swing.ImageIcon;

public class Player extends Thing2D implements Runnable {
    private Field field;
    private boolean alive;
    private int type;

    public Player(int x, int y, Field field,int type) {
        super(x, y);

        this.field = field;
        alive = true;
        this.type = type;

        URL loc;
        if(type == 1)
        	loc = this.getClass().getClassLoader().getResource("hong.png");
        else if(type == 2)
        	loc = this.getClass().getClassLoader().getResource("cheng.png");
        else if(type == 3)
        	loc = this.getClass().getClassLoader().getResource("huang.png");
        else if(type == 4)
        	loc = this.getClass().getClassLoader().getResource("green.png");
        else if(type == 5)
        	loc = this.getClass().getClassLoader().getResource("blue.png");
        else if(type == 6)
        	loc = this.getClass().getClassLoader().getResource("dian.png");
        else if(type == 7)
        	loc = this.getClass().getClassLoader().getResource("zi.png");
        else if(type == 8)
        	loc = this.getClass().getClassLoader().getResource("grandpa.png");
        else if(type <=16 && type >= 11)
        	loc = this.getClass().getClassLoader().getResource("louluo.png");
        else if(type == 17)
        	loc = this.getClass().getClassLoader().getResource("snake.png");
        else if(type == 18)
        	loc = this.getClass().getClassLoader().getResource("xiezi.png");
        else 
        	loc = this.getClass().getClassLoader().getResource("hong.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }

    public  void reset(int x,int y,int type) {
    	alive = true;
    	this.type = type;
    	this.setX(x);
    	this.setY(y);
    }
    
    public boolean live() {
    	return alive;
    }
    
    public void death() {
    	alive = false;
        try {
        	FileOutputStream out = new FileOutputStream("test.txt",true);
        	
        	//out.write((this.x() + "," + this.y()+ " ").getBytes());
        	
        	out.write(("k,"+ this.type + ",\r\n").getBytes());
        	
        	out.close();
        	
        }catch(Exception e) {
        	e.printStackTrace();
        }
    }
    
    public synchronized void move(int x, int y) {
        int nx = this.x() + x;
        int ny = this.y() + y;
        if(field.in_field(nx, ny) && !field.hold(nx, ny, this.type)) {

            try {
            	FileOutputStream out = new FileOutputStream("test.txt",true);
            	
            	//out.write((this.x() + "," + this.y()+ " ").getBytes());
            	
            	if(x != 0) {
            		out.write(("m,"+this.type+ ","+"x,"+x + "\r\n").getBytes());
            		//out.write("\r\n".getBytes());
            	}
            	if(y != 0) {
            		out.write(("m,"+this.type+ ","+"y,"+y + "\r\n").getBytes());
            		//out.write("\r\n".getBytes());
            	}
            	
            	out.close();
            	
            }catch(Exception e) {
            	e.printStackTrace();
            }
            this.setX(nx);
        	this.setY(ny);
        }
    }
    
    public synchronized int fightwith(Player enermy) {
		Random rand = new Random();
		int temp = rand.nextInt(10);
		if(temp > 5) {
			this.death();
			//System.out.println(this.type + ":" + );
			return this.type;
		}
		else {
			enermy.death();
			return enermy.type;
		}
    }
    
    public synchronized void move_to(int x,int y) {
    	if(field.gameover()) move(0,0);
    	else if(x - this.x() < 0) move(-20,0);
    	else if(x - this.x() > 0) move(20,0);
    	else if(y - this.y() < 0) move(0,-20);
    	else if(y - this.y() > 0)move(0,20);
    }

    
    public void run() {
        while (!Thread.interrupted()) {
            Random rand = new Random();

            //this.move(rand.nextInt(10), rand.nextInt(10));
            //this.move(20, 0);
            if(this.live())
            	this.move_to(field.near(this.x(), this.y(), this.type).x(),
            		field.near(this.x(), this.y(), this.type).y());
            try {

                Thread.sleep(rand.nextInt(1000) + 1000);
                this.field.repaint();

            } catch (Exception e) {

            }
        }
    }
}

