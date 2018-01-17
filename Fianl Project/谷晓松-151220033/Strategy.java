package com.gxs;

import java.util.ArrayList;

abstract public class Strategy
{
	protected boolean isFinished=false;
	protected ArrayList<Soldier>soldierList;
	public Strategy(ArrayList<Soldier>soldierList)
	{
		this.soldierList=soldierList;
	}
	protected void setSoldier(ArrayList<Soldier>soldierList)
	{
		this.soldierList=soldierList;
	}
	protected final boolean isFinished()
	{
		return isFinished;
	}
	protected abstract void check();
	public abstract void execute(OrderList orderList);
}

class StEmbattle extends Strategy
{
	private ArrayList<Position>dest;
	
	StEmbattle(ArrayList<Soldier>soldierList,Position key,Dir dir,ShapeItem pattern)
	{
		super(soldierList);
		dest=Shape.getShapeService().makeShape(key, dir, pattern);
	}
	protected void check()
	{
		boolean finish=true;
		for(int i=0;i<dest.size();i++)
			finish&=Position.equal(soldierList.get(i).getPositon(),dest.get(i));
		isFinished=finish;
	}
	public void execute(OrderList orderList)
	{
		for(int i=0;i<dest.size();i++)
			if(soldierList.get(i).isAlive())
				orderList.setOrder(i, OrderFactory.createMt(dest.get(i)));
		check();
	}
}
class StHold extends Strategy
{
	private int round;
	public StHold(ArrayList<Soldier>soldierList,int round)
	{
		super(soldierList);
		this.round=round;
	}
	protected void check()
	{
		round--;
		if(round==0)
			this.isFinished=true;
	}
	public void execute(OrderList orderList)
	{
		for(int i=0;i<soldierList.size();i++)
			if(soldierList.get(i).isAlive())
				orderList.setOrder(i, OrderFactory.createHd());
		check();
	}
}
class StWinOrDie extends Strategy
{
	StWinOrDie(ArrayList<Soldier>soldierList)
	{
		super(soldierList);
	}
	protected void check()
	{
		isFinished=false;
	}
	public void execute(OrderList orderlist)
	{
		int count=0;
		for(int i=0;i<soldierList.size();i++)
			if(soldierList.get(i).isAlive())
			{
				count++;
				orderlist.setOrder(i,OrderFactory.createWD());
			}
		System.out.println("assign mission for "+count+" soldiers");		
	}
}
class StMarch extends Strategy
{
	private int speed;
	private Dir dir;
	private int round;
	public StMarch(ArrayList<Soldier>soldierList,int speed,int round,Dir dir)
	{
		super(soldierList);
		this.speed=speed;
		this.dir=dir;
		this.round=round;
	}
	protected void check()
	{
		round--;
		if(round==0)
			isFinished=true;
	}
	public void execute(OrderList orderList)
	{
		for(int i=0;i<orderList.size();i++)
			if(soldierList.get(i).isAlive())
				orderList.setOrder(i, OrderFactory.createMc(speed, dir));
		check();
	}
}
class StrategyFactory
{
	public static StEmbattle createEb(ArrayList<Soldier>soldierList,Position key,Dir dir,ShapeItem pattern)
	{
		return new StEmbattle(soldierList,key,dir,pattern);
	}
	public static StMarch createMc(ArrayList<Soldier>soldierList,int speed,int round,Dir dir)
	{
		return new StMarch(soldierList,speed,round,dir);
	}
	public static StHold createHd(ArrayList<Soldier>soldierList,int round)
	{
		return new StHold(soldierList,round);
	}
	public static StWinOrDie createWd(ArrayList<Soldier>soldierList)
	{
		return new StWinOrDie(soldierList);
	}
}