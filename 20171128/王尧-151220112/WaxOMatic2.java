import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Car {
    private boolean waxOn = false;
    public synchronized void waxed() {
        waxOn = true;
        notifyAll();
    }
    public synchronized void buffed() {
        waxOn = false;
        notifyAll();
    }
    public synchronized void waitForWaxing() throws InterruptedException {
        while (waxOn == false)
            wait();
    }
    public synchronized void waitForBuffingAndNextWaxing() throws InterruptedException {
        while (waxOn == true)
            wait();
    }
}

class WaxOn implements Runnable {
    private Car car;
    private int id;
    private boolean waxing = false;
    public WaxOn(Car c, int i) {
        car = c;
        id = i;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                car.waitForBuffingAndNextWaxing();
                synchronized (car) {
                    System.out.println("Wax On" + id + ": Wax On!");
                    TimeUnit.MILLISECONDS.sleep(200);
                    car.waxed();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupted");
        }
        System.out.println("Ending Wax On" + id + " task");
    }
}

class WaxOff implements Runnable {
    private Car car;
    public WaxOff(Car c) { car = c; }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                car.waitForWaxing();
                System.out.println("Wax Off!");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupted");
        }
        System.out.println("Ending Wax Off task");
    }
}

public class WaxOMatic2 {
    public static void main(String[] args) throws Exception{
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));
        exec.execute(new WaxOn(car, 1));
        exec.execute(new WaxOn(car, 2));
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
}
