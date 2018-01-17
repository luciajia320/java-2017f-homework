/**************************************************
	> File Name:  Keys.java
	> Author:     Leuckart
	> Time:       2018-01-08 23:00
**************************************************/

import java.util.concurrent.*;

public class Keys
{
	private boolean waxOn=false;

	public synchronized void waxed()
	{
		waxOn=true;
		notifyAll();
	}

	public synchronized void buffed()
	{
		waxOn=false;
		notifyAll();
	}

	public synchronized void waitForWaxing() throws InterruptedException
	{
		while (waxOn==false)
			wait();
	}

	public synchronized void waitForBuffing() throws InterruptedException
	{
		while (waxOn==true)
			wait();
	}
}

