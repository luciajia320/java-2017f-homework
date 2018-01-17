import java.util.concurrent.*;
import java.util.concurrent.locks.*;

class Car {
    private boolean waxOn = false;
    public Lock lock = new ReentrantLock();
    public synchronized void waxed() { // 打蜡
        waxOn = true; // Ready to buff
        notifyAll();
    }
    public synchronized void buffed() { // 抛光
        waxOn = false; // Ready for another coat of wax
        notify();
    }
    public synchronized void waitForWaxing()
            throws InterruptedException { // 等待打蜡
        while(waxOn == false)
            wait();
    }
    public synchronized void waitForBuffing()
            throws InterruptedException { // 等待抛光
        while(waxOn == true)
            wait();
    }
}

class WaxOn implements Runnable {
    private Car car;
    private static int count = 0;
    private int id = ++count;

    public WaxOn(Car c) {
        car = c;
    }

    public void  run() {
        try {
            while (!Thread.interrupted()) {
                car.lock.lock();
                try {
                    if (Thread.interrupted()) break;
                    System.out.println("WaxOn" + id + ": Wax On!");
                    TimeUnit.MILLISECONDS.sleep(200);
                    car.waxed();
                    car.waitForBuffing();
                }  finally {
                    car.lock.unlock();
                    Thread.yield();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending WaxOn" + id + " task");
    }
}

class WaxOff implements Runnable {
    private Car car;
    public WaxOff(Car c) { car = c; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                car.waitForWaxing();
                if (Thread.interrupted()) break;
                System.out.println("Wax Off!");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            }
        } catch(InterruptedException e) {
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
        exec.execute(new WaxOn(car));
        exec.execute(new WaxOn(car));
        TimeUnit.SECONDS.sleep(5); // Run for a while...
        exec.shutdownNow(); // Interrupt all tasks
    }
}