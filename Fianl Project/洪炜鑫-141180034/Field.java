/**************************************************
	> File Name:  Field.java
	> Author:     Leuckart
	> Time:       2018-01-05 22:41
**************************************************/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.*;

public class Field
{
	private static final int height=12;
	private static final int width=20;

	private static String[][] area=new String[height][width];

	private static ArrayList<Creature> creatureQueue=new ArrayList<Creature>();
	private static ArrayList<Position> positionQueue=new ArrayList<Position>();

	public static ArrayList<Position> getPositionQueue()
	{
		return positionQueue;
	}

	public static ArrayList<Creature> getCreatureQueue()
	{
		return creatureQueue;
	}

	private static Keys key=new Keys();

	private Policy policy;

	ExecutorService exec=Executors.newCachedThreadPool();

	public void initField()
	{
		creatureQueue.clear();
		positionQueue.clear();
		for(int i=0;i<height;i++)
			for(int j=0;j<width;j++)
				area[i][j]="  ";
	}

	public Field enQueueCreature(Creature[] create)//for HuluBrothers / MonsterBrothers
	{
		creatureQueue.addAll(Arrays.asList(create));
		//for(Creature creat:create)
		//	exec.execute(creat);
		return this;
	}

	public Field enQueueCreature(Creature create)//for Grandfather / Scorpion / Snake
	{
		creatureQueue.add(create);
		//exec.execute(create);
		return this;
	}

	public static void display()
	{
		for(Iterator<Creature> cIter=creatureQueue.iterator();cIter.hasNext();)
		{
			Creature create=cIter.next();
			area[create.getPosition().getX()][create.getPosition().getY()]=create.toString();
		}

		for(String[] i:area)
		{
			for(String j:i)
			{
				System.out.print(j);
			}
			System.out.print("\n");
		}
	}

	public void setPolicy(String s)
	{
		switch(s)
		{
			case "Heyi":
				policy=new Heyi();
				break;
			case "Yanxing":
				policy=new Yanxing();
				break;
			case "Chonge":
				policy=new Chonge();
				break;
			case "Yulin":
				policy=new Yulin();
				break;
			case "Fangyuan":
				policy=new Fangyuan();
				break;
			case "Yanyue":
				policy=new Yanyue();
				break;
			case "Fengshi":
				policy=new Fengshi();
				break;
			default:
				break;
		}

		policy.fillQueue(creatureQueue,positionQueue);

		exec.execute(new WaxOff(key));

		for(Creature create:creatureQueue)
		{
			exec.execute(create.setKey(key));
		}

	}

	public void shutDown()
	{
		
		exec.shutdownNow();
	}
}
