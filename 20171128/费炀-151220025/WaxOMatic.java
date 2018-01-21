//package concurrency.waxomatic;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;

//import static net.mindview.util.Print.*;



//import static net.mindview.util.Print.*;

class Car {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean waxOn = false;

    public Lock waxlock = new ReentrantLock();
    public Condition waxcondition = waxlock.newCondition();

    public void waxed() {
        lock.lock();
        try {
            waxOn = true; // Ready to buff
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public void buffed() {
        lock.lock();
        try {
            waxOn = false; // Ready for another coat of wax
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public void waitForWaxing() throws InterruptedException {
        lock.lock();
        try {
            while (waxOn == false)
                condition.await();
        }finally {
            lock.unlock();
        }
    }

    public void waitForBuffing() throws InterruptedException {
        lock.lock();
        try {
            while (waxOn == true)
                condition.await();
        }finally {
            lock.unlock();
        }
    }
}

class WaxOn1 implements Runnable {
    private Car car;

    public WaxOn1(Car c) {
        car = c;
    }

    public  void run() {
        try {
            while (!Thread.interrupted()) {

                car.waxlock.lock();
                try {
                    car.waitForBuffing();
                    System.out.println("WaxOn1: Wax On! \n");
                    TimeUnit.MILLISECONDS.sleep(200);
                    car.waxed();
                }finally {
                    car.waxlock.unlock();

                }
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending Wax On task");
    }
}

class WaxOn2 implements Runnable {
    private Car car;

    public WaxOn2(Car c) {
        car = c;
    }

    public  void run() {

        try {
            while (!Thread.interrupted()) {

                car.waxlock.lock();
                try {

                    car.waitForBuffing();
                    System.out.println("WaxOn2: Wax On! \n");
                    TimeUnit.MILLISECONDS.sleep(200);
                    car.waxed();


                }finally {
                    car.waxlock.unlock();
                }
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
        exec.execute(new WaxOn1(car));
        exec.execute(new WaxOn2(car));
        TimeUnit.SECONDS.sleep(20); // Run for a while...
        exec.shutdownNow(); // Interrupt all tasks
    }
}
