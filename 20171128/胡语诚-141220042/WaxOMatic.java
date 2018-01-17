import java.util.concurrent.*;
import java.util.Random;

import static net.mindview.util.Print.*;

class Car {
    private boolean waxOn = true;
    private boolean is_waxing = false;
    private int num;
    private int ran;

    Car(int n) {
        num = n;
    }


    public synchronized void waxing() {
        is_waxing = true;
    }

    public synchronized void waxing_done() {
        is_waxing = false;
    }

    public synchronized void waxed() {
        waxOn = true; // Ready to buff
        notifyAll();
    }

    public synchronized void buffed() {
        waxOn = false; // Ready for another coat of wax
        Random random = new Random();
        ran = random.nextInt(num) + 1;
        notifyAll();
    }

    public synchronized void waitForWaxing()
            throws InterruptedException {
        while (waxOn == false)
            wait();
    }

    public synchronized void waitForBuffing(int no)
            throws InterruptedException {
        while (waxOn == true || is_waxing == true || ran != no)
            wait();
    }
}

class WaxOn implements Runnable {
    private Car car;
    private int no;

    public WaxOn(Car c,int n) {
        car = c;
        no = n;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.waitForBuffing(no);
                car.waxing();
                printnb("WaxOn"+ no + ":Wax On! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waxing_done();
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
                printnb("Wax Off! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
                car.waitForWaxing();
            }
        } catch (InterruptedException e) {
            print("Exiting via interrupt");
        }
        print("Ending Wax Off task");
    }
}

public class WaxOMatic {
    public static void main(String[] args) throws Exception {
        Car car = new Car(2);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));
        exec.execute(new WaxOn(car,1));
        exec.execute(new WaxOn(car,2));
        TimeUnit.SECONDS.sleep(5); // Run for a while...
        exec.shutdownNow(); // Interrupt all tasks
    }
}