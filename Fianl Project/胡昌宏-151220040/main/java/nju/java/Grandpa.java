package nju.java;

import java.awt.Image;
import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;


public class Grandpa extends Creature implements Runnable {
    //private Field field;
	
    public Grandpa(int x, int y, int rank,Field field) {
        super(x, y,field);
        this.isGoodMan = true;
        this.field = field;
        this.rank=rank;
        this.state = State.right;
        URL loc = this.getClass().getClassLoader().getResource("Grandpa-right.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }
    
    
    @Override
    public void moveRight() {
    	if(x() >= 14*field.getSpace() + field.getOffset()) {
    		state = State.left;
            setImage(new ImageIcon(getClass().getClassLoader().getResource("Grandpa-left.png")).getImage());
            moveLeft();
        }
    	else
    		super.moveRight();
    }

    @Override
    public void moveLeft() {
    	if(x() <= 0*field.getSpace() + field.getOffset()) {
    		state = State.right;
            setImage(new ImageIcon(getClass().getClassLoader().getResource("Grandpa-right.png")).getImage());
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
    	field.huluCount--;
    	setImage(new ImageIcon(getClass().getClassLoader().getResource("Grandpa-dead.png")).getImage());
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