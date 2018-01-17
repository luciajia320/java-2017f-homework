package concurrency;
import java.util.concurrent.*;


class Car {
    private boolean waxOn = false;
    private boolean iswax = false;
    public synchronized void waxed() {
        waxOn = true; // Ready to buff
        notifyAll();
    }

    public synchronized void buffed() {
        waxOn = false; // Ready for another coat of wax
        iswax = false;
        notifyAll();
    }

    public synchronized void waitForWaxing()
            throws InterruptedException {
        while (waxOn == false)
            wait();
    }
    public synchronized void isWaxing()throws InterruptedException {
    	while(iswax == true)
    		wait();
    	iswax = true;
    }
    public synchronized void waitForBuffing()
            throws InterruptedException {
        while (waxOn == true)
            wait();
    }
}

class WaxOn implements Runnable {
    private Car car;
    private static int count = 1;
    private int id = count++;
    public WaxOn(Car c) {
        car = c;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
            	 car.isWaxing();
                System.out.println("WaxOn"+id +":Wax On! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitForBuffing();
            }
        } catch (InterruptedException e) {
            System.out.print("Exiting via interrupt");
        }
        System.out.print("Ending Wax On task");
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
            System.out.print("Exiting via interrupt");
        }
        System.out.print("Ending Wax Off task");
    }
}

public class WaxOMatic {
    public static void main(String[] args) throws Exception {
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));
        exec.execute(new WaxOn(car));
        exec.execute(new WaxOn(car));
        TimeUnit.SECONDS.sleep(5); // Run for a while...
        exec.shutdownNow(); // Interrupt all tasks
    }
}
