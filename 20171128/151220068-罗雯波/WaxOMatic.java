import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
    private String name;
    private static Lock waxLock = new ReentrantLock();

    public WaxOn(String n, Car c) {
        name = n;
        car = c;
    }

    public void run() {
        Lock lock = waxLock;
        try {
            while (!Thread.interrupted()) {
                car.waitForBuffing();
                if (lock.tryLock()) {
                    // 由于调度的中断可能发生在上一个 waitForBuffering() 方法刚返回的时刻
                    // 为避免连续的 WaxOn 在此再次 waitForBuffing()
                    car.waitForBuffing();
                    try {
                        System.out.println(name + ":Wax On! ");
                        TimeUnit.MILLISECONDS.sleep(200);
                        car.waxed();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupt");
        }
        System.out.println(name + ":Ending WaxOn task");
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
        exec.execute(new WaxOn("WaxOn1", car));
        exec.execute(new WaxOn("WaxOn2", car));
        TimeUnit.SECONDS.sleep(5); // Run for a while...
        exec.shutdownNow(); // Interrupt all tasks
    }
}
