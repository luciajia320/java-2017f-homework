package nju.java;

import java.awt.Image;
import java.net.URL;
import java.util.Random;
import javax.swing.ImageIcon;
import java.io.*;
enum State{left,right,up,down,dead}

public class Creature extends Thing2D{
	//protected transient Field field;
	protected Field field;
	State state;
	boolean isGoodMan;
	int rank;
	
	public Creature(int x, int y, Field field) {
	        super(x, y);
	        this.field = field;
	 }
	
	public void move(int x, int y) {
        int nx = this.x() + x;
        int ny = this.y() + y;
        String s = "";
  
        	switch(this.rank) {
        	case 1:s = "Hu1";break;
        	case 2:s = "Hu2";break;
        	case 3:s = "Hu3";break;
        	case 4:s = "Hu4";break;
        	case 5:s = "Hu5";break;
        	case 6:s = "Hu6";break;
        	case 7:s = "Hu7";break;
        	case 8:s = "Grandpa";break;
        	case 9:s = "Snake";break;
        	case 10:s = "Scorpion";break;
        	case 11:s = "Goblin1";break;
        	case 12:s = "Goblin2";break;
        	case 13:s = "Goblin3";break;
        	case 14:s = "Goblin4";break;
        	case 15:s = "Goblin5";break;
        	case 16:s = "Goblin6";break;
        	case 17:s = "Goblin7";break;
        	}
        String str=new String(s + " " + this.x() + " " + this.y() + " " + nx + " " + ny);
		BufferedWriter out = null;     
        try {     
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("record.txt", true)));               
            out.write(str);
            out.newLine();    
        } catch (Exception e) {     
            e.printStackTrace();     
        } finally {     
            try {     
                if(out != null){  
                    out.close();     
                }  
            } catch (IOException e) {     
                e.printStackTrace();     
            }     
        }     

        
      
        this.setX(nx);
        this.setY(ny);
    }
	
	public void moveUp() {
        move(0,-field.getSpace());
    }
	public void moveDown() {
        move(0,field.getSpace());
    }
	public void moveLeft() {
		//state = State.left;
        move(-field.getSpace(),0);
    }
	public void moveRight() {
		//state = State.right;
        move(field.getSpace(),0);
    }
	
	public void goDie() {
		state = State.dead;
		String s="";
    	switch(this.rank) {
    	case 1:s = "Hu1-dead";break;
    	case 2:s = "Hu2-dead";break;
    	case 3:s = "Hu3-dead";break;
    	case 4:s = "Hu4-dead";break;
    	case 5:s = "Hu5-dead";break;
    	case 6:s = "Hu6-dead";break;
    	case 7:s = "Hu7-dead";break;
    	case 8:s = "Grandpa-dead";break;
    	case 9:s = "Snake-dead";break;
    	case 10:s = "Scorpion-dead";break;
    	case 11:s = "Goblin1-dead";break;
    	case 12:s = "Goblin2-dead";break;
    	case 13:s = "Goblin3-dead";break;
    	case 14:s = "Goblin4-dead";break;
    	case 15:s = "Goblin5-dead";break;
    	case 16:s = "Goblin6-dead";break;
    	case 17:s = "Goblin7-dead";break;
    	}
    String str=new String(s + " " + this.x() + " " + this.y());
	BufferedWriter out = null;     
    try {     
        out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("record.txt", true)));               
        out.write(str);
        out.newLine();    
    } catch (Exception e) {     
        e.printStackTrace();     
    } finally {     
        try {     
            if(out != null){  
                out.close();     
            }  
        } catch (IOException e) {     
            e.printStackTrace();     
        }     
    }     
	}
	
	public void fighting(Creature x, Creature y) {
		Random rand = new Random();
		int flag = rand.nextInt(10);
        if (flag <= 5)
        	x.goDie();
        else
        	y.goDie();
	}
	
	public boolean meet(int x, int y) {
		for (int i = 0; i < field.position.size(); i++) {
			Creature tmp = (Creature) field.position.get(i);
			if(tmp.state != State.dead && !tmp.equals(this) && tmp.isGoodMan != this.isGoodMan) {
				if((x == tmp.x()||x-1 == tmp.x() || x == tmp.x()-1) && y == tmp.y()) {
					fighting(tmp,this);
					return true;
				}
			}
		}
		return false;
	}
}
