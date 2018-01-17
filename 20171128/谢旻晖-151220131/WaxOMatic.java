import java.util.concurrent.*;
import java.util.concurrent.locks.*;

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

    public WaxOn(Car c,int no) {
        this.car = c;
        this.no=no;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
               synchronized (car){
                car.waitForBuffing();
                printnb("Wax" + no + " On!\n");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
               }
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
                synchronized (car) {
                    car.waitForWaxing();
                    printnb("Wax Off!\n");
                    TimeUnit.MILLISECONDS.sleep(200);
                    car.buffed();
                }
            }
        } catch (InterruptedException e) {
            print("Exiting via interrupt\n");
        }
        print("Ending Wax Off task\n");
    }
}

public class WaxOMatic {
    public static void main(String[] args) throws Exception {
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));
        exec.execute(new WaxOn(car,0));
        exec.execute(new WaxOn(car,1));
        TimeUnit.SECONDS.sleep(5); // Run for a while...
        exec.shutdownNow(); // Interrupt all tasks
    }
}