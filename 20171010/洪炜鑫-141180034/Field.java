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

public class Field
{
	private static final int height=12;
	private static final int width=20;

	private String[][] area=new String[height][width];

	private ArrayList<Position> positionQueue;
	private ArrayList<Creature> creatureQueue;

	private Policy policy;

	public ArrayList<Position> getPositionQueue()
	{
		return positionQueue;
	}

	public ArrayList<Creature> getCreatureQueue()
	{
		return creatureQueue;
	}

	public void initField()
	{
		positionQueue=new ArrayList<Position>();
		creatureQueue=new ArrayList<Creature>();
		for(int i=0;i<height;i++)
			for(int j=0;j<width;j++)
				area[i][j]="  ";
	}

	public Field enQueueCreature(Creature[] create)//for HuluBrothers / MonsterBrothers
	{
		creatureQueue.addAll(Arrays.asList(create));
		return this;
	}

	public Field enQueueCreature(Creature create)//for Grandfather / Scorpion / Snake
	{
		creatureQueue.add(create);
		return this;
	}

	public void display()
	{
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
		
		policy.fillQueue(this);

		Iterator<Creature> cIter=creatureQueue.iterator();
		while (cIter.hasNext())
		{
			Creature create=cIter.next();
			Position pos=create.getPosition();
			area[pos.getX()][pos.getY()]=create.toString();
		}
	}
}
