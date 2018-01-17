package nju.java;

import java.awt.Image;
import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;

public class Huluwa extends Creature implements Runnable {
    //private Field field;
    //int rank;
    
    public Huluwa(int x, int y, int rank, Field field) {
        super(x, y,field);
        this.isGoodMan = true;
        this.rank = rank;
        this.field = field;
        this.state = State.right;
        URL loc = this.getClass().getClassLoader().getResource("Hu1-right.png");
        if(rank == 1)
        	loc = this.getClass().getClassLoader().getResource("Hu1-right.png");
        else if(rank == 2)
        	loc = this.getClass().getClassLoader().getResource("Hu2-right.png");
        else if(rank == 3)
        	loc = this.getClass().getClassLoader().getResource("Hu3-right.png");
        else if(rank == 4)
        	loc = this.getClass().getClassLoader().getResource("Hu4-right.png");
        else if(rank == 5)
        	loc = this.getClass().getClassLoader().getResource("Hu5-right.png");
        else if(rank == 6)
        	loc = this.getClass().getClassLoader().getResource("Hu6-right.png");
        else if(rank == 7)
        	loc = this.getClass().getClassLoader().getResource("Hu7-right.png");
        
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }
    
    public int getRank() {
    	return this.rank;
    }
    
    @Override
    public void moveRight() {
    	if(x() >= 14*field.getSpace() + field.getOffset()) {
    		state = State.left;
    		//System.out.println(rank);
    		switch(rank){
    			case 1:setImage(new ImageIcon(getClass().getClassLoader().getResource("Hu1-left.png")).getImage());break;
    			case 2:setImage(new ImageIcon(getClass().getClassLoader().getResource("Hu2-left.png")).getImage());break;
    			case 3:setImage(new ImageIcon(getClass().getClassLoader().getResource("Hu3-left.png")).getImage());break;
    			case 4:setImage(new ImageIcon(getClass().getClassLoader().getResource("Hu4-left.png")).getImage());break;
    			case 5:setImage(new ImageIcon(getClass().getClassLoader().getResource("Hu5-left.png")).getImage());break;
    			case 6:setImage(new ImageIcon(getClass().getClassLoader().getResource("Hu6-left.png")).getImage());break;
    			case 7:setImage(new ImageIcon(getClass().getClassLoader().getResource("Hu7-left.png")).getImage());break;
    			default:setImage(new ImageIcon(getClass().getClassLoader().getResource("Hu1-left.png")).getImage());break;
    		}
            moveLeft();
        }
    	else
    		super.moveRight();
    }

    @Override
    public void moveLeft() {
    	if(x() <= 0*field.getSpace() + field.getOffset()) {
    		state = State.right;
    		switch(rank){
			case 1:setImage(new ImageIcon(getClass().getClassLoader().getResource("Hu1-right.png")).getImage());break;
			case 2:setImage(new ImageIcon(getClass().getClassLoader().getResource("Hu2-right.png")).getImage());break;
			case 3:setImage(new ImageIcon(getClass().getClassLoader().getResource("Hu3-right.png")).getImage());break;
			case 4:setImage(new ImageIcon(getClass().getClassLoader().getResource("Hu4-right.png")).getImage());break;
			case 5:setImage(new ImageIcon(getClass().getClassLoader().getResource("Hu5-right.png")).getImage());break;
			case 6:setImage(new ImageIcon(getClass().getClassLoader().getResource("Hu6-right.png")).getImage());break;
			case 7:setImage(new ImageIcon(getClass().getClassLoader().getResource("Hu7-right.png")).getImage());break;
			default:setImage(new ImageIcon(getClass().getClassLoader().getResource("Hu1-right.png")).getImage());break;
		}
            
            moveRight();
        }
    	else
    		super.moveLeft();
    }
    
    @Override
    public void moveUp() {
    	if(y() <= 1*field.getSpace() + field.getOffset()) {
    		moveDown();
    	}
    	else
    		super.moveUp();
    }
    
    @Override
    public void moveDown() {
    	if(y() >= 7*field.getSpace() + field.getOffset()) {
    		moveUp();
    	}
    	else
    		super.moveDown();
    }
    
    @Override
    public void goDie() {
    	super.goDie();
    	state = State.dead;
    	 
    	switch(rank){
		case 1:setImage(new ImageIcon(getClass().getClassLoader().getResource("Hu1-dead.png")).getImage());break;
		case 2:setImage(new ImageIcon(getClass().getClassLoader().getResource("Hu2-dead.png")).getImage());break;
		case 3:setImage(new ImageIcon(getClass().getClassLoader().getResource("Hu3-dead.png")).getImage());break;
		case 4:setImage(new ImageIcon(getClass().getClassLoader().getResource("Hu4-dead.png")).getImage());break;
		case 5:setImage(new ImageIcon(getClass().getClassLoader().getResource("Hu5-dead.png")).getImage());break;
		case 6:setImage(new ImageIcon(getClass().getClassLoader().getResource("Hu6-dead.png")).getImage());break;
		case 7:setImage(new ImageIcon(getClass().getClassLoader().getResource("Hu7-dead.png")).getImage());break;
		default:setImage(new ImageIcon(getClass().getClassLoader().getResource("Hu1-dead.png")).getImage());break;
    	}
    }
    
    
   public int findSnake() {
	   boolean isExist = true;
	   int sX = -1;
    	for (int i = 0; i < field.position.size(); i++) {
    		Creature tmp = (Creature) field.position.get(i);
			if(tmp instanceof Snake) {
				sX = tmp.y();
				if(tmp.state == State.dead) //蛇死
					isExist = false;
				else { //蛇没死
					for (int j = 0; j < field.position.size(); j++) {
						Creature tmp2 = (Creature) field.position.get(j);
						if(tmp2.state != State.dead && tmp2 instanceof Huluwa) {//有活的葫芦娃在同一行
							if(tmp2.x() == tmp.x())
								isExist = false;
						}
					}
				}
			}
    	}
		if(isExist)
			return sX;
		else
			return -1;
    }
    
   public int findScorpion() {
	   boolean isExist = true;
	   int sX = -1;
    	for (int i = 0; i < field.position.size(); i++) {
    		Creature tmp = (Creature) field.position.get(i);
			if(tmp instanceof Scorpion) {
				sX = tmp.y();
				if(tmp.state == State.dead) //蝎子死
					isExist = false;
				else { //蝎子没死
					for (int j = 0; j < field.position.size(); j++) {
						Creature tmp2 = (Creature) field.position.get(j);
						if(tmp2.state != State.dead && tmp2 instanceof Huluwa) {//有活的葫芦娃在同一行
							if(tmp2.x() == tmp.x())
								isExist = false;
						}
					}
				}
			}
    	}
		if(isExist)
			return sX;
		else
			return -1;
    }
   
   public int findGoblin() {
	   int index = -1;
	   for (int i = 0; i < field.position.size(); i++) {
   		Creature tmp = (Creature) field.position.get(i);
   		int tmpX = tmp.y();
   		if(tmp instanceof Goblin) {
   			if(tmp.state == State.dead)
   				index = -1;
   			else {
   				if(this.y() > tmpX)
   					index = 1;
   				else
   					index = 2;
   			}
   		}
	   }
	   return index;
   }
    void findEnemy() {
    	int sX = findSnake();
    	int cX = findScorpion();
    	if(sX != -1) {
    		if(this.y() > sX)
    			moveUp();
    		else
    			moveDown();
    	}
    	else  if(cX != -1) {
    		if(this.y() > cX)
    			moveUp();
    		else
    			moveDown();
    	}
    	else {
    		switch(findGoblin()) {
    		case 1:moveUp();break;
    		case 2:moveDown();break;
    		default:moveUp();break;
    		}
    	}
    }
    
    public void run() {
    	int index = 0;
    	while (!Thread.interrupted()) {
    		if(field.enemyCount == 0)
    			break;
    		if(field.huluCount == 0)
    			break;
    		index++;
            Random rand = new Random();
            if(index >= 20 && state != State.dead) {
            	index = 0;
            	//this.moveUp();
            	findEnemy();
            }
            else {
            if(state == State.left)
            	this.moveLeft();
            if(state == State.right)
            	this.moveRight();
            }
            try {

                Thread.sleep(rand.nextInt(1000) + 1000);
                this.field.repaint();
                if(state == State.left)
                	meet(x()-field.getSpace(), y());
                if(state == State.right)
                	meet(x()+field.getSpace(), y());

            } catch (Exception e) {

            }
        }
    }
}
