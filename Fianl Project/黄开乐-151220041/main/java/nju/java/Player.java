package nju.java;


import java.awt.Image;
import java.net.URL;
import java.util.Random;
import javax.swing.ImageIcon;
import java.io.*;

public class Player extends Thing2D implements Runnable {
    protected Field field;
	protected Character Huluwa;
	protected Statement state = Statement.Right;
    private final int OFFSET = 10;
    private final int SPACE = 60;
    public Player(int x, int y, Character c,Field field) {
        super(x, y);
        Huluwa = c;
        this.field = field;

        //URL loc = this.getClass().getClassLoader().getResource("player.png");
        //ImageIcon iia = new ImageIcon(loc);
        //Image image = iia.getImage();
        //this.setImage(image);
		//Huluwa = c;
		URL loc;
		int rank = Huluwa.ordinal();
		switch(rank) {
		case 0:loc = this.getClass().getClassLoader().getResource("red-right.png");		break;
		case 1:loc = this.getClass().getClassLoader().getResource("orange-right.png");	break;
		case 2:loc = this.getClass().getClassLoader().getResource("yellow-right.png");	break;
		case 3:loc = this.getClass().getClassLoader().getResource("green-right.png");	break;
		case 4:loc = this.getClass().getClassLoader().getResource("cyan-right.png");	break;
		case 5:loc = this.getClass().getClassLoader().getResource("blue-right.png");	break;
		case 6:loc = this.getClass().getClassLoader().getResource("purple-right.png");	break;
		case 7:loc = this.getClass().getClassLoader().getResource("grandfather-left.png");	break;
		case 8:loc = this.getClass().getClassLoader().getResource("snake-left.png");	break;
		case 9:loc = this.getClass().getClassLoader().getResource("Scorpion-left.png");	break;
		case 10:loc = this.getClass().getClassLoader().getResource("minion-left.png");	break;
		default:loc = this.getClass().getClassLoader().getResource("minion-left.png");	break;
		}
		ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
		super.setImage(image);
    }
    
    public void fight(Player a, Player b) {
    	Random rand = new Random();
		int x = rand.nextInt(100);
		if(b.Huluwa.ordinal() < 7 && a.Huluwa.ordinal() > 7) {
			Player temp = a;
			a = b;
			b = temp;
		}
    	if(a.Huluwa.ordinal() < 7 && b.Huluwa.ordinal() > 7) {
    		if(a.Huluwa.ordinal() == 0) {
    			if(b.Huluwa.ordinal() == 8||b.Huluwa.ordinal() == 9) {
    				if(x < 40)
    					a.turn_dead();
    				else
    					b.turn_dead();
    			}
    			else {
    				if(x < 30)
    					a.turn_dead();
    				else
    					b.turn_dead();
    			}
    		}
    		else if(a.Huluwa.ordinal() == 1) {
    			if(b.Huluwa.ordinal() == 8||b.Huluwa.ordinal() == 9) {
    				if(x < 50)
    					a.turn_dead();
    				else
    					b.turn_dead();
    			}
    			else {
    				if(x < 40)
    					a.turn_dead();
    				else
    					b.turn_dead();
    			}
    		}
    		else if(a.Huluwa.ordinal() == 2) {
    			if(b.Huluwa.ordinal() == 8||b.Huluwa.ordinal() == 9) {
    				if(x < 40) {
    					x = rand.nextInt(100);
    					if(x < 80)
    						a.turn_dead();
    				}
    				else
    					b.turn_dead();
    			}
    			else {
    				if(x < 30)
    					a.turn_dead();
    				else
    					b.turn_dead();
    			}
    		}
    		else if(a.Huluwa.ordinal() == 3) {
    			if(b.Huluwa.ordinal() == 8||b.Huluwa.ordinal() == 9) {
    				if(x < 50)
    					a.turn_dead();
    				else
    					b.turn_dead();
    			}
    			else {
    				if(x < 40)
    					a.turn_dead();
    				else
    					b.turn_dead();
    			}
    		}
    		else if(a.Huluwa.ordinal() == 4) {
    			if(b.Huluwa.ordinal() == 8||b.Huluwa.ordinal() == 9) {
    				if(x < 50)
    					a.turn_dead();
    				else
    					b.turn_dead();
    			}
    			else {
    				if(x < 40)
    					a.turn_dead();
    				else
    					b.turn_dead();
    			}
    		}
    		else if(a.Huluwa.ordinal() == 5) {
    			x = rand.nextInt(100);
				if(x < 20)
					return;
    			if(b.Huluwa.ordinal() == 8||b.Huluwa.ordinal() == 9) {
    				if(x < 50)
    					a.turn_dead();
    				else
    					b.turn_dead();
    			}
    			else {
    				if(x < 40)
    					a.turn_dead();
    				else
    					b.turn_dead();
    			}
    		}
    		else if(a.Huluwa.ordinal() == 6) {
    			if(b.Huluwa.ordinal() == 8||b.Huluwa.ordinal() == 9) {
    				if(x < 50)
    					a.turn_dead();
    				else
    					b.turn_dead();
    			}
    			else {
    				if(x < 40)
    					a.turn_dead();
    				else
    					b.turn_dead();
    			}
    		}
    	}
    }
    
    boolean check_fight(int x, int y) {
    	if(Huluwa == Character.Green_cala || Huluwa == Character.Blue_cala) {
    		int xp = x - x() + x;
    		int yp = y - y() + y;
    		for(int i = 8;i < field.Creature.size();i++) {
        		Player item = (Player) field.Creature.get(i);
        		if(item.state != Statement.Dead) {
        			if(xp == item.x() && yp == item.y()) {
        				Random rand = new Random();
        				int dis = rand.nextInt(100);
        				if(dis < 25)
        					item.turn_dead();
        			}
        		}
        	}
    	}
    	
    	for(int i = 0;i < field.Creature.size();i++) {
    		Player item = (Player) field.Creature.get(i);
    		if( !item.equals(this)&& item.state != Statement.Dead) {
    			if(x == item.x() && y == item.y()) {
    				if(item.Huluwa.ordinal() == 7) {
    					return false;
    				}
    				fight(item, this);
    				return true;
    			}
    		}
    	}
    	return false;
    }

    public void move(int x, int y) {   
        	if(check_fight(this.x() + x, this.y() + y))
        		return;
    		int nx = this.x() + x;
    		int ny = this.y() + y;
    		int s = 0;
    		if(Huluwa == Character.Red_cala)
    			s = 1;
    		else if(Huluwa == Character.Orange_cala) 
    			s = 2;
    		else if(Huluwa == Character.Yellow_cala) 
    			s = 3;
    		else if(Huluwa == Character.Green_cala) 
    			s = 4;
    		else if(Huluwa == Character.Cyan_cala) 
    			s = 5;
    		else if(Huluwa == Character.Blue_cala) 
    			s = 6;
    		else if(Huluwa == Character.Purple_cala) 
    			s = 7;
    		String str=new String(s + " " + this.x() + " " + this.y() + " " + nx + " " + ny);
    		BufferedWriter out = null;     
            try {     
                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("data.txt", true)));               
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
    
    public void run() {
        while (!Thread.interrupted()) {
            Random rand = new Random();
            if(field.get_completed() == true)
            	break;
            //this.move(rand.nextInt(10), rand.nextInt(10));
        	this.move(SPACE, 0);
            try {

                Thread.sleep( rand.nextInt(1000)+1000);
                this.field.repaint();

            } catch (Exception e) {

            }
        }
    }
    
    public void turn_left() {
    	state = Statement.Left;
    	URL loc;
		int rank = Huluwa.ordinal();
		switch(rank) {
		case 0:loc = this.getClass().getClassLoader().getResource("red-left.png");		break;
		case 1:loc = this.getClass().getClassLoader().getResource("orange-left.png");	break;
		case 2:loc = this.getClass().getClassLoader().getResource("yellow-left.png");	break;
		case 3:loc = this.getClass().getClassLoader().getResource("green-left.png");	break;
		case 4:loc = this.getClass().getClassLoader().getResource("cyan-left.png");	break;
		case 5:loc = this.getClass().getClassLoader().getResource("blue-left.png");	break;
		case 6:loc = this.getClass().getClassLoader().getResource("purple-left.png");	break;
		case 7:loc = this.getClass().getClassLoader().getResource("grandfather-left.png");	break;
		case 8:loc = this.getClass().getClassLoader().getResource("snake-left.png");	break;
		case 9:loc = this.getClass().getClassLoader().getResource("Scorpion-left.png");	break;
		case 10:loc = this.getClass().getClassLoader().getResource("minion-left.png");	break;
		default:loc = this.getClass().getClassLoader().getResource("minion-left.png");	break;
		}
		ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
		super.setImage(image);
    }
    
    public void turn_right() {
    	state = Statement.Right;
    	URL loc;
		int rank = Huluwa.ordinal();
		switch(rank) {
		case 0:loc = this.getClass().getClassLoader().getResource("red-right.png");		break;
		case 1:loc = this.getClass().getClassLoader().getResource("orange-right.png");	break;
		case 2:loc = this.getClass().getClassLoader().getResource("yellow-right.png");	break;
		case 3:loc = this.getClass().getClassLoader().getResource("green-right.png");	break;
		case 4:loc = this.getClass().getClassLoader().getResource("cyan-right.png");	break;
		case 5:loc = this.getClass().getClassLoader().getResource("blue-right.png");	break;
		case 6:loc = this.getClass().getClassLoader().getResource("purple-right.png");	break;
		case 7:loc = this.getClass().getClassLoader().getResource("grandfather-right.png");	break;
		case 8:loc = this.getClass().getClassLoader().getResource("snake-right.png");	break;
		case 9:loc = this.getClass().getClassLoader().getResource("Scorpion-right.png");	break;
		case 10:loc = this.getClass().getClassLoader().getResource("minion-right.png");	break;
		default:loc = this.getClass().getClassLoader().getResource("minion-right.png");	break;
		}
		ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
		super.setImage(image);
    }
    
    public void turn_dead() {
    	state = Statement.Dead;
    	URL loc;
		int rank = Huluwa.ordinal();
		switch(rank) {
		case 0:loc = this.getClass().getClassLoader().getResource("red-dead.png");		break;
		case 1:loc = this.getClass().getClassLoader().getResource("orange-dead.png");	break;
		case 2:loc = this.getClass().getClassLoader().getResource("yellow-dead.png");	break;
		case 3:loc = this.getClass().getClassLoader().getResource("green-dead.png");	break;
		case 4:loc = this.getClass().getClassLoader().getResource("cyan-dead.png");	break;
		case 5:loc = this.getClass().getClassLoader().getResource("blue-dead.png");	break;
		case 6:loc = this.getClass().getClassLoader().getResource("purple-dead.png");	break;
		case 7:loc = this.getClass().getClassLoader().getResource("grandfather-dead.png");	break;
		case 8:loc = this.getClass().getClassLoader().getResource("snake-dead.png");	break;
		case 9:loc = this.getClass().getClassLoader().getResource("Scorpion-dead.png");	break;
		case 10:loc = this.getClass().getClassLoader().getResource("minion-dead.png");	break;
		default:loc = this.getClass().getClassLoader().getResource("minion-dead.png");	break;
		}
		if(rank < 6) {
			for(int i = 0;i < field.Creature.size();i++) {
	    		Player item = (Player) field.Creature.get(i);
	    		if( item.equals(this)) {
	    			Player item_1 = (Player) field.Creature.get(i+1);
	    			item_1.state = Statement.Right;
	    		}
	    	}
		}
		int s = 0;
		if(Huluwa == Character.Red_cala)
			s = 1;
		else if(Huluwa == Character.Orange_cala) 
			s = 2;
		else if(Huluwa == Character.Yellow_cala) 
			s = 3;
		else if(Huluwa == Character.Green_cala) 
			s = 4;
		else if(Huluwa == Character.Cyan_cala) 
			s = 5;
		else if(Huluwa == Character.Blue_cala) 
			s = 6;
		else if(Huluwa == Character.Purple_cala) {
			s = 7;
			field.stop_game();
		}
		else if(Huluwa == Character.Scorpion_man) 
			s = 8;
		else if(Huluwa == Character.snake_women) 
			s = 9;
		else if(Huluwa == Character.Toad1) 
			s = 10;
		else if(Huluwa == Character.Toad2) 
			s = 11;
		else if(Huluwa == Character.Toad3) 
			s = 12;
		else if(Huluwa == Character.Toad4) 
			s = 13;
		else if(Huluwa == Character.Toad5) 
			s = 14;
		else if(Huluwa == Character.Toad6) 
			s = 15;
		String str=new String(100 + " " + s);
		BufferedWriter out = null;     
        try {     
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("data.txt", true)));               
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

		ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
		super.setImage(image);
    }
}