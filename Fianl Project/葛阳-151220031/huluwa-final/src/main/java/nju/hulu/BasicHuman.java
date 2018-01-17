package nju.hulu;
import java.awt.Image;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.ImageIcon;

public class BasicHuman implements Creature,Runnable{

	int sleeptime=800;
	static Lock lock=new ReentrantLock();
	Position<Creature> position;
	Field field;
	Image image;
	Image dead;
	State  state=State.ALIVE;//
	protected Type type;
	Group group;
	URL currentloc;
	URL loc1;
	URL loc2;
	
	
	public Type getType()
	{
		return type;
	}
	public Position<Creature> getPosition()
	{
		return position;
	}
	boolean withinRow(int x,int y)//不出现跨列
	{
		if (Math.abs(y)==field.getCh())
			return true;
		if (Math.abs(y)>field.getCh())
			return false;
		int r=x%field.getCh();
	
		if (r+y>=0&&r+y<field.getCh())
		{
			return true;
		}
		else return false;
		
	
	}
	private Boolean withinField(int x)
	{
		if (x>=0 && x<field.getPositions().size() )
		{
		return true;
		}
		else return false;
		
	}
	
	protected Boolean shouldFight(Creature a)
	{
		int x=field.getPositions().indexOf(a.getPosition());
		int first=x-2*field.getCh()-2;
		int second=x-field.getCh()-2;
		int third=x-2;
		int forth=x+field.getCh()-2;
		int fifth=x+2*field.getCh()-2;
		int[] pos= {first,second,third,forth,fifth};		
		for (int i=1;i<pos.length-1;i++)
		{
			for (int j=1;j<4;j++)
			{
				int p=pos[i]+j;
				if (withinField(p))
				{
					Position<Creature> temp=field.getPositions().get(p);
					if (temp.exist)
					{
						if(temp.getHolder().getState()==State.ALIVE&&temp.getHolder().getGroup()!=a.getGroup())//判断输赢
						{
						Judge theJudge=new Judge();						
						if(!theJudge.defeat(temp.getHolder().getType(), a.getType()))
						{	temp.getHolder().setState(State.DEAD);
							return false;
						}
						
						else
							a.setState(State.DEAD);
						return true;
						
						}
					}
			}
		}		
	}
		return false;
	}
	
	public void setPosition(Position<Creature> positionToSet)
	{
		if(positionToSet.exist)
		{	
			return ;
		}
		
		int i=0;
	
		if (this.position!=null)
		{
			this.position.exist=false;
		}
		this.position=positionToSet;
		this.position.setHolder(this);
		shouldFight(this);
	
		
	}
	
	/*
	public boolean smallerThan(Creature creature,int type)
	{
		return false;
	}*/
	
	
	public void move(int distance)
	{
		
		int current=this.getPosition().getX()*field.getCh()+this.getPosition().getY();
		
		if (withinField(distance+current)&&withinRow(current,distance))
		{
			lock.lock();
			this.setPosition(field.getPositions().get(distance+current));
			lock.unlock();
		}
	}

	
	public Image getImage() {
		
		return image; 
		//return null;
	}
	public  void run() {
			  
        while (!Thread.interrupted()) {
        	
        	if (state==State.ALIVE)
        	{
        		Random rand = new Random();
        		 try {
                     
                     
                 	Thread.sleep(rand.nextInt(sleeptime)+ sleeptime);
               

                  } catch (Exception e) {
                 	 
                  }
        	
            int x=rand.nextInt(5);
            if(x<3)
            	  this.move((1)*field.getCh());
            else 
            		this.move((-1)*field.getCh());
            	int y=rand.nextInt(3);
            	if(y>=1)
              	this.move(1);
            else if(y<1)
              	this.move(-1);//纵向移动
       
            
           
        		}
        		
                
        else if (state==State.DEAD)//dead
        {  
        		break ;
        }
        else if (state==State.END)
        {
        		break;
        }
    
        }

  }

	public void setState(State i) {
		
		if (i==State.DEAD)
		{
			image=dead;
			currentloc=loc2;
			
		}
		state=i;
		// TODO Auto-generated method stub
		
	}

	public  State getState() {
		return state;
		// TODO Auto-generated method stub
		
	}
	public Group getGroup() {
		return group;
	}
	
	public URL getImgURL()
	{
		return currentloc;
	}
	
 
	
}
