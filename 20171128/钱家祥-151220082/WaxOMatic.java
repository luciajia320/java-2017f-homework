package concurrency.waxomatic;

import java.util.Random;
import java.util.concurrent.*;

import static net.mindview.util.Print.*;

class Car {
    private boolean waxOn = false;
    private boolean isOne = false;

    public synchronized void waxed() {
        waxOn = true; // Ready to buff
        isOne = true;
        notifyAll();
    }

    public synchronized void buffed() {
        waxOn = false; // Ready for another coat of wax
        notifyAll();
    }
    
    public synchronized int waxing() {
    	if(isOne) {
    		isOne = false;
    		return 1;
    	}
    	else
    		return 2;
    }
    
    
    public synchronized void waitForWaxing()
            throws InterruptedException {
        while (waxOn == false)
            wait();
    }

    public synchronized void waitForBuffing()
            throws InterruptedException {
        while (waxOn == true)
            wait();
    }
}

class WaxOn implements Runnable {
    private Car car;
    private int id;
    
    public WaxOn(Car c, int i) {
        car = c;
        id = i;
    }
    public void run() {
        try {
            while (!Thread.interrupted()) {
            	if(car.waxing() != 1) {
	            	System.out.println("Wax On" + id + ": Wax On! ");
	            	TimeUnit.MILLISECONDS.sleep(200);
	            	car.waxed();
            	}
            	else car.waitForWaxing();
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
                System.out.println("Wax Off! ");
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
        exec.execute(new WaxOn(car,1));
        exec.execute(new WaxOn(car,2));
        TimeUnit.SECONDS.sleep(5); // Run for a while...
        exec.shutdownNow(); // Interrupt all tasks
    }
}


