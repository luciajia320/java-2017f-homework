import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RHWaxomatic {
    static public void main(String[] args) throws Exception {
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));
        exec.execute(new WaxOn(car));
        exec.execute(new WaxOn(car));
        TimeUnit.SECONDS.sleep(60); // Run for a while...
        exec.shutdownNow(); // Interrupt all tasks
    }
}

class Car {
    private boolean waxOn = false;
    private boolean lockOn = false;
    private boolean lockOff = false;

    private int test = 0;

    public synchronized void testAdd() {
        test++;
        if (test > 1) {
            System.exit(-1);
        }
    }

    public synchronized void testSub() {
        test--;
        if (test < 0) {
            System.exit(-1);
        }
    }

    public synchronized boolean askForLockOn() {
        if (!lockOn && !waxOn) {
            lockOn = true;
            return true;
        } else {
            return false;
        }
    }

    public synchronized boolean askForLockOff() {
        if (!lockOff && waxOn) {
            lockOff = true;
            return true;
        } else {
            return false;
        }
    }

    public synchronized void waxed() {
        lockOn = false;
        waxOn = true; // Ready to buff
        notifyAll();
    }

    public synchronized void buffed() {
        lockOff = false;
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
    static private int ID = 1;
    private int id = ID++;
    private Car car;

    public WaxOn(Car c) {
        car = c;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                if (car.askForLockOn()) {
                    car.testAdd();
                    System.out.println("Wax On" + this.id + "!");
                    TimeUnit.MILLISECONDS.sleep(1000);
                    car.waxed();
                    car.waitForBuffing();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending Wax On task");
    }
}

class WaxOff implements Runnable {
    static private int ID = 1;
    private int id = ID++;
    private Car car;

    public WaxOff(Car c) {
        car = c;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                if (car.askForLockOff()) {
                    car.testSub();
                    car.waitForWaxing();
                    System.out.println("Wax Off" + this.id + "!");
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
