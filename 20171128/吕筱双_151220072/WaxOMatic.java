//package concurrency.waxomatic;

import java.util.concurrent.*;

import static com.sun.deploy.trace.Trace.print;
//import static net.mindview.util.Print.*;

class Car {
    private boolean waxOn = false;
    private boolean chooseOne = false;

    public synchronized void waxed() {
        if(waxOn == false ) {
            waxOn = true; // Ready to buff
            chooseOne= true;
            notifyAll();
        }
    }

    public synchronized void buffed() {
        if(waxOn == true ) {
            waxOn = false; // Ready for another coat of wax
            chooseOne = false;
            notifyAll();
            //notify();
        }
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
    public synchronized void waitForAnotherWaxed()
            throws InterruptedException {
        while (chooseOne == true)
            wait();
        chooseOne = true;
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
                car.waitForAnotherWaxed();
                System.out.println("WaxOn1:Wax On! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitForBuffing();
            }
        } catch (InterruptedException e) {
            print("Exiting via interrupt");
        }
        print("Ending Wax On task");
    }
}
class WaxOn2 implements  Runnable{
    private Car car;

    public WaxOn2(Car c) {
        car = c;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.waitForAnotherWaxed();
                System.out.println("WaxOn2:Wax On! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitForBuffing();
            }
        } catch (InterruptedException e) {
            print("Exiting via interrupt");
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
                System.out.println("Wax Off! ");
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
        exec.execute(new WaxOn1(car));
        exec.execute(new WaxOn2(car));
        TimeUnit.SECONDS.sleep(5); // Run for a while...
        exec.shutdownNow(); // Interrupt all tasks
    }
}