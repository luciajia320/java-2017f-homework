/**************************************************
	> File Name:  WaxOMatic.java
	> Author:     Leuckart
	> Time:       2018-01-08 00:10
**************************************************/

import java.util.concurrent.*;

class Car
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

class WaxOn1 implements Runnable
{
	private Car car;

	public WaxOn1(Car c)
	{
		car=c;
	}

	public synchronized void run()
	{
		synchronized(car)
		{
			try
			{
				while(!Thread.interrupted())
				{
					car.waitForBuffing();
					System.out.println("WaxOn1: Wax On!\n");
					TimeUnit.MILLISECONDS.sleep(200);
					car.waxed();
				}
			}
			catch(InterruptedException e)
			{
				System.out.println("Exiting via interrupt\n");
			}
			System.out.println("Ending Wax On task\n");
		}
	}
}

class WaxOn2 implements Runnable
{
	private Car car;

	public WaxOn2(Car c)
	{
		car=c;
	}

	public synchronized void run()
	{
		synchronized(car)
		{
			try
			{
				while(!Thread.interrupted())
				{
					car.waitForBuffing();
					System.out.println("WaxOn2: Wax On!\n");
					TimeUnit.MILLISECONDS.sleep(200);
					car.waxed();
				}
			}
			catch(InterruptedException e)
			{
				System.out.println("Exiting via interrupt\n");
			}
			System.out.println("Ending Wax On task\n");
		}
	}
}

class WaxOff implements Runnable
{
	private Car car;

	public WaxOff(Car c)
	{
		car=c;
	}

	public void run()
	{
		try
		{
			while(!Thread.interrupted())
			{
				car.waitForWaxing();
				System.out.println("Wax Off! \n");
				TimeUnit.MILLISECONDS.sleep(200);
				car.buffed();
			}
		}
		catch(InterruptedException e)
		{
			System.out.println("Exiting via interrupt\n");
		}
		System.out.println("Ending Wax Off task\n");
	}
}

public class WaxOMatic
{
	public static void main(String[] args) throws Exception
	{
		Car car=new Car();
		ExecutorService exec=Executors.newCachedThreadPool();
		exec.execute(new WaxOff(car));
		exec.execute(new WaxOn1(car));
		exec.execute(new WaxOn2(car));
		TimeUnit.SECONDS.sleep(10);
		exec.shutdownNow();
	}
}
