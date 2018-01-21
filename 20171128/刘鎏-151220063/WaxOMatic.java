import java.util.concurrent.*;

class Car {
    private boolean waxOn = false;
    private boolean occupy = false;

    public synchronized void ifIsOccupied()
            throws InterruptedException{
        while(occupy == true)
            wait();
        occupy = true;
    }

    public synchronized void waxed() {
        waxOn = true;
        notifyAll();
    }

    public synchronized void buffed() {
        waxOn = false;
        occupy = false;
        System.out.println("Wax Off! ");
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

class WaxOn1 implements Runnable {
    private Car car;

    public WaxOn1(Car c) {
        car = c;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.ifIsOccupied();
                System.out.println("WaxOn1: Wax On! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitForBuffing();
            }
        } catch (InterruptedException e) {
            System.out.println("WaxOn1 Exiting via interrupt");
        }
        System.out.println("Ending WaxOn1 task");
    }
}

class WaxOn2 implements Runnable {
    private Car car;

    public WaxOn2(Car c) {
        car = c;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.ifIsOccupied();
                System.out.println("WaxOn2: Wax On! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitForBuffing();
            }
        } catch (InterruptedException e) {
            System.out.println("WaxOn2 Exiting via interrupt");
        }
        System.out.println("Ending WaxOn2 task");
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
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            }
        } catch (InterruptedException e) {
            System.out.println("WaxOff Exiting via interrupt");
        }
        System.out.println("Ending WaxOff task");
    }
}

public class WaxOMatic {
    public static void main(String[] args) throws Exception {
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));
        exec.execute(new WaxOn1(car));
        exec.execute(new WaxOn2(car));
        TimeUnit.SECONDS.sleep(5); // Run for a while...
        exec.shutdownNow(); // Interrupt all tasks
    }
}