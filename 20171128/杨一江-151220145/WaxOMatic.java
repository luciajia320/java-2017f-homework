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

    public WaxOn(Car c) {
        car = c;
    }

    public WaxOn(Car c, String _name){
        car = c;
        name = _name;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (car) {
                    System.out.println(name + ": " + "Wax On! ");
                    TimeUnit.MILLISECONDS.sleep(200);
                    car.waxed();
                }
                // in order to demonstrate the rank-independent feature
                {
                    java.util.Random rand = new java.util.Random();
                    TimeUnit.MILLISECONDS.sleep(rand.nextInt(30) * rand.nextInt(100));
                }
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
                synchronized (car) {
                    System.out.println("Wax Off! ");
                    TimeUnit.MILLISECONDS.sleep(200);
                    car.buffed();
                }
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

        WaxOn WaxOn1 = new WaxOn(car, "WaxOn1");
        WaxOn WaxOn2 = new WaxOn(car, "WaxOn2");

        exec.execute(WaxOn1);
        exec.execute(WaxOn2);
        TimeUnit.SECONDS.sleep(5); // Run for a while...
        exec.shutdownNow(); // Interrupt all tasks
    }
}