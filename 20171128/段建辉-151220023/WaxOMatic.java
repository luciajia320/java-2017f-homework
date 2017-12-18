import java.util.concurrent.*;

class Car {
    private boolean waxOn = false;
    volatile boolean waxChoice = true;

    public synchronized void waxed() {
        // Ready to buff
        waxOn = true;
        waxChoice = true;
        notifyAll();
    }

    public synchronized void buffed() {
        // Ready for another coat of wax
        waxOn = false;
        notifyAll();
    }

    public synchronized void blockAnotherWax() {
        waxChoice = false;
        waxOn = false;
    }

    public synchronized void waitForCar()
            throws InterruptedException {
        while (!waxChoice) {
            wait();
        }
    }

    public synchronized void waitForWaxing()
            throws InterruptedException {
        while (waxOn == false) {
            wait();
        }
    }

    public synchronized void waitForBuffing()
            throws InterruptedException {
        while (waxOn == true) {
            wait();
        }
    }
}

class WaxOn implements Runnable {
    private Car car;
    int ID;
    public WaxOn(Car c, int id) {
        car = c;
        ID = id;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.waitForCar();
                car.blockAnotherWax();
                System.out.print("WaxOn" + ID + ": ");
                System.out.println("Wax On! ");
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

    @Override
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
        exec.execute(new WaxOn(car, 1));
        exec.execute(new WaxOn(car, 2));
        // Run for a while...
        TimeUnit.SECONDS.sleep(5);
        // Interrupt all tasks
        exec.shutdownNow();
    }
}