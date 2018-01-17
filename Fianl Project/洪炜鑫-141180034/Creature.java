/**************************************************
	> File Name:  Creature.java
	> Author:     Leuckart
	> Time:       2018-01-05 22:40
**************************************************/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.*;

public class Creature implements Runnable
{
	private Position position=new Position();
	private Keys key;

	public Creature setKey(Keys k)
	{
		key=k;
		return this;
	}

	public void setPosition(Position position)
	{
		this.position=position;
		position.setCreature(this);
	}

	public String toString()
	{
		return "无";
	}

	public Position getPosition()
	{
		return position;
	}

	public synchronized void run()
	{
		synchronized(key)
		{
			//System.out.println(this.getClass().getSimpleName());
			try
			{
				while(!Thread.interrupted())
				{//System.out.println(this.getClass().getSimpleName());
					
					ArrayList<Creature> cQueue=Field.getCreatureQueue();

					Position myPos=this.getPosition();

					String className=this.getClass().getSimpleName();
					//System.out.println(className);
					if(className=="Huluwa")
					{
						key.waitForBuffing();
						Position newPos=new Position(myPos.getX(),myPos.getY()+2);
						boolean isExist=false;
						for(Creature c:cQueue)
						{
							if(c.getPosition()==newPos)
								isExist=true;
						}
						if(isExist==true)//zhandou!
						{
							;
						}
						else
						{
							this.setPosition(newPos);
							System.out.print(this.getPosition().getX());
							System.out.print(" , ");
							System.out.print(this.getPosition().getY());
							System.out.println();
							Field.display();
							for(Creature c:cQueue)
							{
								if(c.getPosition()==myPos)
									c.setPosition(newPos);//System.out.println("Huluwa!");
							}
						}

						TimeUnit.MILLISECONDS.sleep(200);
						key.waxed();
					}
				}
			}
			catch(InterruptedException e)
			{
				//System.out.println("Exiting via interrupt.\n");
			}
			//System.out.println("Ending.\n");
		}
	}

}

class WaxOff implements Runnable
{
	private Keys key;

	public WaxOff(Keys k)
	{
		this.key=k;
	}

	public void run()
	{
		try
		{
			while(!Thread.interrupted())
			{
				key.waitForWaxing();
				//System.out.println("Wax Off!\n");
				TimeUnit.MILLISECONDS.sleep(200);
				key.buffed();
			}
		}
		catch(InterruptedException e)
		{
			//System.out.println("Exiting via interrupt\n");
		}
		//System.out.println("Ending Wax Off task\n");
	}
}
