import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WaxOMatic {
    public static void main(String[] args){
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));
        exec.execute(new WaxOn(car));
        exec.execute(new WaxOn(car));
        try{ TimeUnit.SECONDS.sleep(5); }
        catch (Exception e){ }// Run for a while...
        exec.shutdownNow(); // Interrupt all tasks
    }
}

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

class WaxOn implements Runnable {
    private Car car;
    private int id;
    private static int num = 0;

    public WaxOn(Car c) {
        car = c;
        id = ++WaxOn.num;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (car) {
                    System.out.println("WaxOn" + id + ":Wax On! ");
                    TimeUnit.MILLISECONDS.sleep(500);
                    car.waxed();
                    car.waitForBuffing();
                }
            }
        } catch (InterruptedException e)    {
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
                synchronized (car) {
                    car.waitForWaxing();
                    System.out.println("Wax Off! ");
                    TimeUnit.MILLISECONDS.sleep(500);
                    car.buffed();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending Wax Off task");
    }
}

