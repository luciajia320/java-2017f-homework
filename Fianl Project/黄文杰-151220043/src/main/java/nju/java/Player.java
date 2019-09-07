package nju.java;


import java.awt.Image;
import java.net.URL;
import java.util.Random;
import javax.swing.ImageIcon;
enum DIR{
	UP,DOWN,LEFT,RIGHT,FREE,STILL
}
public class Player extends Thing2D implements Runnable {
    private Field field;
    private DIR dir;
    public DIR getDir() {
		return dir;
	}
	int team;
    public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}
	private int strength;
	private String name;
    private boolean alive;
    private boolean search;
    private boolean fighting;
    private String pic;
   
    public boolean isFighting() {
		return fighting;
	}

	public int getStrength() {
		return strength;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public Player(String name,int x, int y, Field field,String pic,int team,int strength) {
        super(x, y);
        this.name=name;
        this.field = field;

        URL loc = this.getClass().getClassLoader().getResource(pic);
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
        this.dir=DIR.RIGHT;
        this.strength=strength;
        this.team=team;
        this.alive=true;
        this.pic=pic;
        this.search=false;
        this.fighting=false;
	}
	

    public void move(int x, int y,boolean replay) {
        int nx = this.x() + x*this.field.OFFSET;
        int ny = this.y() + y*this.field.OFFSET;
        if(nx<0||ny<0||nx>field.getBoardWidth()||ny>field.getBoardHeight()){
        	return;
        }
        if(field.available(nx, ny)){
        	this.setX(nx);
        	this.setY(ny);
        	if(!replay)
        		field.writeRecord(this.name+"´Ó("+this.x()+","+this.y()+")"+"ÒÆ¶¯µ½("+nx+","+ny+")\n");
        }
        if(!replay)
        	field.encounter(this);
    }
    public void moveTest(int x,int y){
    	 int nx = this.x() + x*80;
         int ny = this.y() + y*80;

         if(field.available(nx, ny)){
         	this.setX(nx);
         	this.setY(ny);
         }
    }
    public String getName() {
		return name;
	}

	public void attack(){
    	URL loc = this.getClass().getClassLoader().getResource("attack.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
        search=false;
        this.fighting=true;
        this.setDirStill();
        //this.sleep(2000);
    }
    public void die(){
    	URL loc = this.getClass().getClassLoader().getResource("die.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
        this.alive=false;
        this.setDirStill();
        Thread.interrupted();
    }
    public void normal(){
    	URL loc = this.getClass().getClassLoader().getResource(pic);
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
        //field.search(this);
        this.fighting=false;
        search=true;
    }
    public void setDirUp(){
    	this.dir=DIR.UP;
    }
    public void setDirDown(){
    	this.dir=DIR.DOWN;
    }
    public void setDirLeft(){
    	this.dir=DIR.LEFT;
    }
    public void setDirRight(){
    	this.dir=DIR.RIGHT;
    }
    public void setDirStill(){
    	this.dir=DIR.STILL;
    }
    public void sleep(int time){
    	try {

            Thread.sleep(time);
            this.field.repaint();

        } catch (Exception e) {

        }
    }
    public void run() {
    	try {
    		while (this.alive) {
            
            	Random rand = new Random();
	            if(search==true)
	            	if(!field.search(this))
	            		throw new InterruptedException();
	            switch(this.dir){
		            case UP:this.move(0, 1,false);break;
		            case DOWN:this.move(0, -1,false);break;
		            case RIGHT:this.move(1, 0,false);break;
		            case LEFT:this.move(-1, 0,false);break;
		            case FREE:this.move(rand.nextInt(2), rand.nextInt(2),false);break;
		            case STILL:break;
	            }
	            //this.move(rand.nextInt(10), rand.nextInt(10));
	            
	
	             Thread.sleep(rand.nextInt(1000) + 1000);
	             this.field.repaint();        
    		}
    	} catch (Exception e) {
    		//System.out.println("Interrupted Thread exit");
        }finally{
        	//System.out.println("Thread exit");
        }
    }
}