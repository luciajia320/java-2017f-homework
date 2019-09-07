package nju.java;

import java.awt.Image;
import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;

public class Snake extends Creature implements Runnable {
    //private Field field;
	
    public Snake(int x, int y, int rank, Field field) {
        super(x, y,field);
        this.isGoodMan = false;
        this.field = field;
        this.rank = rank;
        this.state = State.left;
        URL loc = this.getClass().getClassLoader().getResource("Snake-left.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }
    
    @Override
    public void moveRight() {
    	if(x() >= 14*field.getSpace() + field.getOffset()) {
    		state = State.left;
            setImage(new ImageIcon(getClass().getClassLoader().getResource("Snake-left.png")).getImage());
            moveLeft();
        }
    	else
    		super.moveRight();
    }

    @Override
    public void moveLeft() {
    	if(x() <= 0*field.getSpace() + field.getOffset()) {
    		state = State.right;
            setImage(new ImageIcon(getClass().getClassLoader().getResource("Snake-right.png")).getImage());
            moveRight();
        }
    	else
    		super.moveLeft();
    }
    
    @Override
    public void goDie() {
    	super.goDie();
    	state = State.dead;
    	field.enemyCount--;
    	setImage(new ImageIcon(getClass().getClassLoader().getResource("Snake-dead.png")).getImage());
    }
    
    public void run() {
    	while (!Thread.interrupted()) {
    		if(field.enemyCount == 0 || field.huluCount == 0)
    			break;
            Random rand = new Random();

            if(state == State.left)
            	this.moveLeft();
            if(state == State.right)
            	this.moveRight();
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
