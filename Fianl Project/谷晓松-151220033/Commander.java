package com.gxs;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public  class Commander extends Fighter
{
	protected OrderList orderList=new OrderList();
	protected ArrayList<Soldier>soldierList=new ArrayList<Soldier>();
	//用于与scheduler的同步
	private Scheduler scheduler;
	
	protected Strategy curStrategy=null;
	
	private ArrayList<Strategy>strategyList=new ArrayList<Strategy>();
	private int pStrategy=0;
	protected final Strategy nextStrategy()
	{
		if(pStrategy==strategyList.size())
			return null;
		return strategyList.get(pStrategy++);
	}
	protected final void addStrategy(Strategy s)
	{
		strategyList.add(s);
	}
	
	protected void loadStrategy()
	{
		
	}
	public Commander(String name,int speed,int power,Camp camp)
	{
		super(name,speed,power,camp);
	}
	public void addSoldier(Soldier s)
	{
		s.setOrder(orderList.add());
		soldierList.add(s);
	}
	public void setScheduler(Scheduler s)
	{
		scheduler=s;
	}
	
	private void issueOrder()
	{
		for(int i=0;i<soldierList.size();i++)
			if(soldierList.get(i).isAlive())
					orderList.issueOrder(i);
					//orderList.issueOrder(i,orderList.get(i).getOrderItem());
	}
	public void run()
	{
		try {
			while(!Thread.interrupted())
			{
				//等待被调度
				System.out.println(getName()+ " waiting for being scheduled");
				scheduler.waitForScheduler();
				//做出策略，传达命令
				makeStrategy();
				issueOrder();
				//等待执行
				System.out.println(getName()+" waiting for execution");
				orderList.waitingForExecution();
				//执行完毕
				scheduler.finish();
			}
		}catch(InterruptedException e)
		{
			System.out.println(getName()+": exits via interruption");
		}
	}
	protected void KIA()
	{
		this.killSelf();
	}
	//may override for different commanders
	protected void makeStrategy()
	{
		if(curStrategy==null||curStrategy.isFinished())
			curStrategy=nextStrategy();
		if(curStrategy==null)
			return;
		curStrategy.execute(orderList);
	}
}