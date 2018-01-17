package niuxuCharacter;
import nju.java.*;


import java.awt.Image;
import java.net.URL;
import java.util.Random;
import javax.swing.ImageIcon;

public class Player extends Thing2D implements Runnable {
    private Field field;
    public Player() {
    	super();
    }
    public Player(Point p, Field field) {
        super(p);

        this.field = field;
    }
    
    public boolean isRowHaveEnemy(Field field) {
    	for (int i = 0; i < 16; i++) {
            if (field.getPoints()[i][this.y()].getHolder()!=null && field.getPoints()[i][this.y()].getHolder().getSide()!=this.getSide())
            	return true;
    	}
    	return false;
    }
    
    public boolean noEnemy() {
    	for(int i=0; i<16; i++) {
    		for(int j=0; j<8; j++) {
    			if(field.getPoints()[i][j].getHolder()!=null && field.getPoints()[i][j].getHolder().getSide()!=this.getSide())
    				return false;
    		}
    	}
    	return true;
    }
    public void move() {           //角色移动
    	int lenth = 50;
    	//距离最近的点的坐标
        int near_x = -1;
        int near_y = -1;
        for (int i = 0; i < 16; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (field.getPoints()[i][j].getHolder()!=null && field.getPoints()[i][j].getHolder().getSide()!=this.getSide()) {
                    int distance = Math.abs(this.x() - i) + Math.abs(this.y() - j);
                    if (distance < lenth) {
                        lenth = distance;
                        near_x = i;
                        near_y = j;
                    }
                }
            }
        }
        //距离小于1就打起来
        if (lenth == 1) {
            if (Math.random() > 0.5) {
            	story.writeStory(this.name+ " killed "+field.getPoints()[near_x][near_y].getHolder().name+"\r\n");
            	field.getPoints()[near_x][near_y].getHolder().setImage("resources/seat.png");
            	field.getPoints()[near_x][near_y].getHolder().setIsDead(true);
            	field.getPoints()[near_x][near_y].setHolder(null);
            	//中断该player的线程
            }
            else {
            	story.writeStory(this.name+ " is killed by "+field.getPoints()[near_x][near_y].getHolder().name+"\r\n");
            	this.setImage("resources/seat.png");
            	this.setIsDead(true);
            	field.getPoints()[this.x()][this.y()].setHolder(null);
            	Thread.currentThread().interrupt();
            }
        }
        //否则看这一排还有没有对手
        else if (isRowHaveEnemy(field)) {
        	int move_x=0;
        	for (int i = 0; i < 16; i++) {
                if (field.getPoints()[i][this.y()].getHolder()!=null && field.getPoints()[i][this.y()].getHolder().getSide()!=this.getSide()) {
                	move_x=i;
                	break;
                }
        	}
            if (this.x() > move_x) {
                if (field.getPoints()[this.x()-1][this.y()].getHolder()==null) {
                	story.writeStory(this.name+ " moved form "+this.x()+","+this.y()+" to "+(this.x()-1)+","+this.y()+"\r\n");
            		this.p.setHolder(null);
            		this.p=field.getPoints()[this.x()-1][this.y()];
            		this.p.setHolder(this);
                }
            } else {
            	if (field.getPoints()[this.x()+1][this.y()].getHolder()==null) {
            		story.writeStory(this.name+ " moved form "+this.x()+","+this.y()+" to "+(this.x()+1)+","+this.y()+"\r\n");
            		this.p.setHolder(null);
            		this.p=field.getPoints()[this.x()+1][this.y()];
            		this.p.setHolder(this);
                }
            }
        }
        //没有就去其他排
        else {
        	 if (this.y() > near_y) {
                 if (field.getPoints()[this.x()][this.y()-1].getHolder()==null) {
                	 story.writeStory(this.name+ " moved form "+this.x()+","+this.y()+" to "+this.x()+","+(this.y()-1)+"\r\n");
             		this.p.setHolder(null);
             		this.p=field.getPoints()[this.x()][this.y()-1];
             		this.p.setHolder(this);
                 }
             } else {
             	if (field.getPoints()[this.x()][this.y()+1].getHolder()==null) {
             		story.writeStory(this.name+ " moved form "+this.x()+","+this.y()+" to "+(this.x())+","+(this.y()+1)+"\r\n");
             		this.p.setHolder(null);
             		this.p=field.getPoints()[this.x()][this.y()+1];
             		this.p.setHolder(this);
                 }
             }
        }
        if(noEnemy()) {

     		this.p.setHolder(null);
     		this.p=field.getPoints()[15][7];
     		this.p.setHolder(this);
        	story.writeStory(this.name+ "get the fate\r\n");
     		if(this.getSide()=="good")
     			score.good_score++;
     		else
     			score.bad_score++;
     		Thread.currentThread().stop();
        }
        /*if (this.isDead()) {
            //System.out.println("Monster " + this.getClass().getSimpleName() + " Died");
            thread.interrupt();
            break;
        }
        try {
            Thread.sleep((int) (Math.random() * 1000) + 1000);
        } catch (Exception e) {
            thread.interrupt();
            break;
        }*/
    }

    public void run() {
        while (!Thread.interrupted()) {
        	synchronized(field) {
        		if(!this.getIsDead())
        			this.move();
        	}
            try {
                Thread.sleep(500);
                //this.field.repaint();

            } catch (Exception e) {
                this.field.repaint();
            }
        }
    }
}