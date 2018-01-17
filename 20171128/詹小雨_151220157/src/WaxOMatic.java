import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Car {
    private boolean waxOn = false;
    private boolean operate = false;

    public synchronized boolean waxing() {
        if (operate || waxOn)
            return false;
        operate = true;
        notifyAll();
        return true;
    }

    public synchronized void waxed() {
        waxOn = true; // Ready to buff
        operate = false;
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
    private static int count = 0;
    private int no;

    public WaxOn(Car c) {
        car = c;
        count++;
        no = count;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                if (!car.waxing())
                    continue;
                if (no == 1)
                    System.out.println("WaxOn1: Wax On!");
                else
                    System.out.println("WaxOn2: Wax On!");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
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
        exec.execute(new WaxOn(car));
        exec.execute(new WaxOn(car));
        TimeUnit.SECONDS.sleep(5); // Run for a while...
        exec.shutdownNow(); // Interrupt all tasks
    }
}