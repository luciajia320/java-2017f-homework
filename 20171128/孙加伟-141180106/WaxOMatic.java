package concurrency;

import java.util.concurrent.*;

class Car {
	private boolean waxOn = false;
	private boolean haveOne = false;

	public synchronized void waxed(int id) {
		if (haveOne == false) {
			haveOne = true;
			waxOn = true;
			System.out.print("WaxOn" + id + ":Wax On!\n");
			notifyAll();
		}
	}

	public synchronized void buffed() {
		waxOn = false;
		haveOne = false;
		notifyAll();
	}

	public synchronized void waitForWaxing() throws InterruptedException {
		while (waxOn == false)
			wait();
	}

	public synchronized void waitForBuffing() throws InterruptedException {
		while (waxOn == true)
			wait();
	}
}

class WaxOn implements Runnable {
	private Car car;
	private int id;

	public WaxOn(Car c, int id) {
		car = c;
		this.id = id;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(200);
				car.waxed(id);
				car.waitForBuffing();
			}
		} catch (InterruptedException e) {
			System.out.println("Exiting via interrupt");
		}
		System.out.println("Ending Wax" + id + " On task");
	}
}

class WaxOff implements Runnable {
	private Car car;

	public WaxOff(Car c) {
		car = c;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				car.waitForWaxing();
				System.out.print("Wax Off!\n");
				TimeUnit.MILLISECONDS.sleep(200);
				car.buffed();
			}
		} catch (InterruptedException e) {
			System.out.println("Exiting via interrupt");
		}
		System.out.println("Ending Wax Off task");
	}
}

public class WaxOMatic {
	public static void main(String[] args) throws Exception {
		Car car = new Car();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new WaxOff(car));
		exec.execute(new WaxOn(car, 1));
		exec.execute(new WaxOn(car, 2));
		TimeUnit.SECONDS.sleep(10);
		exec.shutdownNow();
	}
}