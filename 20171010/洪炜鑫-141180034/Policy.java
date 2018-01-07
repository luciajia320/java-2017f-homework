/**************************************************
	> File Name:  Policy.java
	> Author:     Leuckart
	> Time:       2018-01-05 22:41
**************************************************/

import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;

public class Policy
{
	protected static Map<String,ArrayList<Position>> location=new HashMap<String,ArrayList<Position>>();
	static
	{
		location.put("Grandfather",new ArrayList<Position>());
		location.put("Huluwa",new ArrayList<Position>());
		location.put("Snake",new ArrayList<Position>());
		location.put("Scorpion",new ArrayList<Position>());
		location.put("Monster",new ArrayList<Position>());
	}

	public void fillQueue(ArrayList<Creature> cQueue,ArrayList<Position> pQueue)
	{
		for(Iterator<Creature> cIter=cQueue.listIterator();cIter.hasNext();)
		{
			Creature create=cIter.next();
			//System.out.println(cIter.next().getClass().getSimpleName());
			String className=create.getClass().getSimpleName();//用一下RTTI
			if(!location.get(className).isEmpty())
			{
				Position pos=location.get(className).get(0);
				location.get(className).remove(0);
				pQueue.add(pos);
				create.setPosition(pos);
			}
			else
			{
				;//throw exception
			}
		}
	}
}
