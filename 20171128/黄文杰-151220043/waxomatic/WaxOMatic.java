package concurrency.waxomatic;

import java.util.concurrent.*;

import static net.mindview.util.Print.*;

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
    int no;
    public WaxOn(Car c,int n) {
        car = c;
        no=n;
    }

    public void run() {
        synchronized(car) {
            try {

                while (!Thread.interrupted()) {
                    car.waitForBuffing();

                    TimeUnit.MILLISECONDS.sleep(200);
                    car.waxed();
                    printnb("WaxOn" + no + ": Wax On! \n");
                    //car.waitForBuffing();
                }
            } catch (InterruptedException e) {
                print("Exiting via interrupt");
            }
        }
        print("Ending Wax On task");
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
                printnb("Wax Off! \n");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            }
        } catch (InterruptedException e) {
            print("Exiting via interrupt");
        }
        print("Ending Wax Off task");
    }
}

public class WaxOMatic {
    public static void main(String[] args) throws Exception {
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));
        exec.execute(new WaxOn(car,1));
        exec.execute(new WaxOn(car,2));

        TimeUnit.SECONDS.sleep(5); // Run for a while...
        exec.shutdownNow(); // Interrupt all tasks
    }
}