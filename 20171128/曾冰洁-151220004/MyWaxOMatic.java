import java.util.concurrent.*;


public class MyWaxOMatic {
    public static void main(String[] args) throws Exception {
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));
        exec.execute(new WaxOn(car, 1));
        exec.execute(new WaxOn(car, 2));
        TimeUnit.SECONDS.sleep(5); // Run for a while...
        exec.shutdownNow(); // Interrupt all tasks
    }
}

class Car {
    private boolean waxOn = false;
    private boolean waxing = false;
    private boolean buffing = false;

    public synchronized void waxed() {
        waxOn = true; // Ready to buff
        waxing = false;
        notifyAll();
    }

    public synchronized void buffed() {
        waxOn = false; // Ready for another coat of wax
        buffing = false;
        notifyAll();
    }

    public synchronized void waitForAvailableToWax()
            throws InterruptedException {
        while (waxing == true || buffing == true || waxOn == true)
            wait();
        waxing = true;
    }

    public synchronized void waitForWaxing()
            throws InterruptedException {
        while (waxOn == false)
            wait();
        buffing = true;
    }

    public synchronized void waitForBuffing()
            throws InterruptedException {
        while (waxOn == true)
            wait();
    }
}

class WaxOn implements Runnable {
    private Car car;
    private int id;

    public WaxOn(Car c, int id) {
        car = c;
        this.id = id;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.waitForAvailableToWax();
                System.out.println(this + "Wax On!");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                Thread.yield();
                car.waitForBuffing();
            }
        } catch (InterruptedException e) {
            System.out.println(this + "Exiting via interrupt");
        }
        System.out.println(this + "Ending Wax On task");
    }

    @Override
    public String toString() {
        return "WaxOn" + this.id + ": ";
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
                System.out.println("Wax Off!");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            }
        } catch (InterruptedException e) {
            System.out.println(this + "Exiting via interrupt");
        }
        System.out.println(this + "Ending Wax Off task");
    }

    @Override
    public String toString() {
        return "WaxOff: ";
    }
}
