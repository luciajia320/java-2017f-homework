package nju.hulu;

import java.awt.Image;
import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;


public class Huluwa  extends BasicHuman implements Creature  {
	
	COLOR color;
	NUMBER num;
	
	
/*	
	@Override
	public boolean smallerThan(Creature creature,int type)
	{
		Huluwa second=(Huluwa)creature;
		if (type==0)
		{
			if (this.color.ordinal()<second.color.ordinal())
				return true;
			else
				return false;
		}
		else if (type==1)
		{
			if (this.num.ordinal()<second.num.ordinal())
				return true;
			else
				return false;
		}
		return true;
		
	}*/
	
	
	Huluwa(COLOR colorToSet,NUMBER numToSet,Position pos,Field field)
	{
		sleeptime=200;
		this.type=Type.HULUWA;
	    this.group=Group.HULU;
		this.field=field;
		this.color=colorToSet;
		this.num=numToSet;
		String picname="huluwa"+num.ordinal();
		picname=picname+".png";
	
		loc1 = this.getClass().getClassLoader().getResource(picname);
	    ImageIcon iia = new ImageIcon(loc1);
	    this.image = iia.getImage();
	    String picname2="huluwaDead"+num.ordinal();
		picname2=picname2+".png";
	
	    loc2 = this.getClass().getClassLoader().getResource(picname2);
	    ImageIcon iib = new ImageIcon(loc2);
	    this.dead = iib.getImage();
	    this.setPosition(pos);
	    currentloc=loc1;
		
		
	}
	 
/*
	public void run() {
		  
        while (!Thread.interrupted()) {
        	if (state==State.ALIVE)
        	{
            Random rand = new Random();
            int x=rand.nextInt(5);
            if (x<1)
            		this.move(-1*field.getCh());
            else
            		this.move(1*field.getCh());
            	//横向移动
            	int y=rand.nextInt(3);
            this.move((y-1));//纵向移动
            shouldFight(this);
            		
            
            try {

               Thread.sleep(rand.nextInt(10) + 600);
                this.field.repaint();

            } catch (Exception e) {

            }
        
        
        }
        
        else if (state==State.DEAD)//dead
        {
    //    System.out.println(Thread.currentThread().getId());
        	break ;
        }
        else if (state==State.END)
        {
        	break;
        }
    
        }

  }
*/
	
}

enum COLOR
{
	
	RED,ORANGE,YELLOW,GREEN,QING,BLUE,PURPLE
	
};

enum NUMBER
{
	FIRST,SECOND,THIRD,FORTH,FIFTH,SIXTH,SEVENTH
	
}
