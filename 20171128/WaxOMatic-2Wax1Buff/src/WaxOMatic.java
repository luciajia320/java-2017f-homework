import java.util.concurrent.*;
import java.util.concurrent.locks.*;

class Car {
    private Lock lockA = new ReentrantLock();
    private Condition conditionOfWaxing = lockA.newCondition();
    private boolean preToWaxOnFlag = false;

    private Lock lockB = new ReentrantLock();
    private Condition conditionOfBuffing = lockB.newCondition();
    private boolean waxOn = false;

    public void preToWaxOn() throws InterruptedException {
        lockA.lock();
        try {
            if(preToWaxOnFlag == false) {
                // Someone's ready to wax the car
                preToWaxOnFlag = true;
            } else {
                while(preToWaxOnFlag) {
                    conditionOfWaxing.await();
                }
                // Wax the car once at a time
                preToWaxOnFlag = true;
            }
        } finally {
            lockA.unlock();
        }
    }

    public void waxed() throws InterruptedException {
        lockB.lock();
        try {
            // Ready to buff
            waxOn = true;
            conditionOfBuffing.signalAll();
        } finally {
            lockB.unlock();
        }
    }

    public void buffed() {
        lockA.lock();
        lockB.lock();
        try {
            // Ready for another coat of wax
            waxOn = false;
            preToWaxOnFlag =false;
            conditionOfBuffing.signalAll();
            conditionOfWaxing.signalAll();
        } finally {
            lockB.unlock();
            lockA.unlock();
        }
    }

    public void waitForWaxing() throws InterruptedException {
        lockB.lock();
        try {
            while (waxOn == false) {
                conditionOfBuffing.await();
            }
        } finally {
            lockB.unlock();
        }
    }

    public void waitForBuffing() throws InterruptedException {
        lockB.lock();
        try {
            while (waxOn == true) {
                conditionOfBuffing.await();
            }
        } finally {
            lockB.unlock();
        }
    }
}

class WaxOn1 implements Runnable {
    private Car car;

    public WaxOn1(Car c) {
        car = c;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.preToWaxOn();
                System.out.println("WaxOn1:Wax On!");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitForBuffing();

            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending Wax On task 1");
    }
}

class WaxOn2 implements Runnable {
    private Car car;

    public WaxOn2(Car c){
        car = c;
    }

    public void run() {
        try {
            while(!Thread.interrupted()){
                car.preToWaxOn();
                System.out.println("WaxOn2:Wax On!");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitForBuffing();
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending Wax On task 2");
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
                System.out.println("Wax Off!\n");
                car.buffed();
                TimeUnit.MILLISECONDS.sleep(200);
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
        exec.execute(new WaxOn1(car));
        exec.execute(new WaxOn2(car));
        exec.execute(new WaxOff(car));
        TimeUnit.SECONDS.sleep(10);
        exec.shutdownNow();
    }
}
