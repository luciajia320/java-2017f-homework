/*
 * 2 waxing hand
 * 1 buffing hand
 */

import java.util.concurrent.*;

class Car {
    private boolean waxOn = false;

    public synchronized void waxed() {
        waxOn = true; // Ready to buff
        notifyAll();
    }

    public synchronized void buffed() {
        waxOn = false; // Ready for another coat of wax
        notifyAll();
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

class WaxController {
    private boolean empty = true;

    public synchronized void isEmpty() throws InterruptedException {
        while (empty == false)
            wait();
        empty = false;
    }

    public synchronized void SetNotEmpty() throws InterruptedException {
        empty = true;
        notifyAll();
    }
}

class WaxOn implements Runnable {
    private static int count = 0;
    private final int id = ++count;
    private Car car;
    private WaxController waxController;

    public WaxOn(Car c, WaxController w) {
        car = c;
        waxController = w;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                waxController.isEmpty();
                System.out.println("Wax On"+id + "!");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitForBuffing();
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Wax Machine " + id + ": Ending Wax On task");
    }
}


class WaxOff implements Runnable {
    private Car car;
    private WaxController waxController;

    public WaxOff(Car c,WaxController w) {
        car = c;
        waxController = w;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.waitForWaxing();
                System.out.println("Wax off!");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
                waxController.SetNotEmpty();
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
        WaxController waxController = new WaxController();

        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car,waxController));
        exec.execute(new WaxOn(car,waxController));
        exec.execute(new WaxOn(car,waxController));
        TimeUnit.SECONDS.sleep(5); // Run for a while...
        exec.shutdownNow(); // Interrupt all tasks
    }
} 

