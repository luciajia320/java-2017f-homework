package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//import static net.mindview.util.Print.*;

class Car {
	private boolean waxOn1 = false;
	private boolean waxOn2 = false;

	public synchronized void waxed(int i) {

		waxOn1 = false;
		waxOn2 = false;
		
		if (i==200) {//1����2�ر� 2����1�ر�
			waxOn1 = true;
		} // Ready to buff
		else {
			waxOn2 = true;
		}
		notifyAll();
	}

	public synchronized void buffed() {
		waxOn1 = false;
		waxOn2 = false;// Ready for another coat of wax
		notifyAll();
	}

	public synchronized void waitForWaxing() throws InterruptedException {
		while (waxOn1 == false & waxOn2 == false)// �߳�1���߳�2��û����Ϳ��
			wait();
	}

	public synchronized void waitForBuffing() throws InterruptedException {
		while (waxOn2 == true | waxOn1 == true)// �߳�1�����߳�2��һ����Ϳ��
			wait();
	}
}

class WaxOn implements Runnable {
	private Car car;
	private int no;

	public WaxOn(Car c, int i) {
		car = c;
		no = i;

	}

	public void run() {

		try {
			while (!Thread.interrupted()) {
				System.out.println("WaxOn" + no + ": Wax On! \n");
				TimeUnit.MILLISECONDS.sleep(200);
				car.waxed(no);
				car.waitForBuffing();
			}
		} catch (InterruptedException e) {
			System.out.println("Exiting via interrupt");
		}
		System.out.println("Ending Wax On task");
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
				System.out.println("Wax Off! \n");
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
		TimeUnit.SECONDS.sleep(10); // Run for a while...
		exec.shutdownNow(); // Interrupt all tasks
	}
}