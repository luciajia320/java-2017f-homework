import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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
        while (!waxOn)
            wait();
    }

    public synchronized void waitForBuffing()
            throws InterruptedException {
        while (waxOn)
            wait();
    }
}

class WaxOn implements Runnable {
    private String name;
    private Car car;
    public WaxOn(Car car, String name) {
        this.car = car;
        this.name = name;
    }

    @Override
    public synchronized void run() {
        try {
            synchronized (car) {
                while (!Thread.interrupted()) {
                    car.waitForBuffing();
                    System.out.println(name + ": Wax On!");
                    TimeUnit.MILLISECONDS.sleep(200);
                    car.waxed();
                }
            }
        } catch (InterruptedException e) {
            System.out.println(name + ": Exiting via interrupt!");
        }
        System.out.println(name + ": Ending Wax On task!");
    }
}

class WaxOff implements Runnable {
    private Car car;

    public WaxOff(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.waitForWaxing();
                System.out.println("Wax Off!");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            }
        } catch (InterruptedException e) {
            System.out.println("Wax Off: Exiting via interrupt!");
        }
        System.out.println("Wax Off: Ending Wax Off task!");
    }
}

public class TestWaxOn2 {
    public static void main(String[] args) throws InterruptedException {
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));
        exec.execute(new WaxOn(car, "WaxOn1"));
        exec.execute(new WaxOn(car, "WaxOn2"));
        TimeUnit.SECONDS.sleep(10);
        exec.shutdownNow();
    }
}