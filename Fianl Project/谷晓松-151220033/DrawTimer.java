package com.gxs;



public class DrawTimer
{
	private boolean ready=true;
	
	public synchronized void waitForMove() throws InterruptedException
	{
		while(!ready)
			wait();
	}
	public synchronized void moveOver()
	{
		ready=true;
		notifyAll();
	}
	public synchronized void drawOver()
	{
		ready=false;
		notifyAll();
	}
	public synchronized void waitForDraw() throws InterruptedException
	{
		while(ready)
			wait();
	}
}