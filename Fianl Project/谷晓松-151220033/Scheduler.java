package com.gxs;

import java.util.ArrayList;

public class Scheduler
{
	private boolean myturn=false;
	
	public synchronized void waitForScheduler()throws InterruptedException
	{
		while(myturn==false)
			wait();
	}
	public synchronized void waitForExecution()throws InterruptedException
	{
		while(myturn)
			wait();
	}
	public synchronized void Scheduled()
	{
		myturn=true;
		notifyAll();
	}
	public synchronized void finish()
	{
		myturn=false;
		notifyAll();
	}
}
class SchedulerList
{
	private ArrayList<Scheduler>schedulerList=new ArrayList<Scheduler>();
	private int current=0;
	
	public Scheduler add()
	{
		Scheduler s=new Scheduler();
		schedulerList.add(s);
		return s;
	}
	public Scheduler get(int i)
	{
		return schedulerList.get(i);
	}
	public synchronized void scheduleOneTask() throws InterruptedException
	{
		System.out.println("schedule one task");
		schedulerList.get(current).Scheduled();
		/*
		while(schedulerList.get(current).isFinished())
			wait();
		*/
		schedulerList.get(current).waitForExecution();
		current=(current+1)%schedulerList.size();
		System.out.println("schedule over");
	}
}