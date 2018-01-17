package concurrency.waxomatic;

/**
 * Created by Yuyang Wei on 2017/12/12.
 */
import java.util.concurrent.*;

class Car {
    private boolean waxOn1 = false;
    private boolean waxOn2 = false;
    public synchronized void waxed1() {
        waxOn1 = true; // Ready to buff
        notifyAll();
    }

    public synchronized void waxed2() {
        waxOn2 = true; // Ready to buff
        notifyAll();
    }

    public synchronized void buffed() {
        waxOn1 = false; // Ready for another coat of wax
        waxOn2 = false;
        notifyAll();
    }

    public synchronized void waitForWaxing()
            throws InterruptedException {
        while (waxOn1 == false&&waxOn2==false)
            wait();
    }

    public synchronized void waitForBuffing()
            throws InterruptedException {
        while (waxOn1 == true||waxOn2==true)
            wait();
    }
}

class WaxOn1 implements Runnable{
    private Car car;

    public WaxOn1(Car c) {
        car = c;
    }
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.print("WaxOn1: Wax On!\n");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed1();
                car.waitForBuffing();
            }
        } catch (InterruptedException e) {
            System.out.println("Wax1 exiting via interrupt");
        }
        System.out.println("Ending Wax1 On task");
    }
}//WaxOn1

class WaxOn2 implements Runnable{
    private Car car;

    public WaxOn2(Car c) {
        car = c;
    }
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.print("WaxOn2: Wax On! \n");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed2();
                car.waitForBuffing();
            }
        } catch (InterruptedException e) {
            System.out.println("Wax2 exiting via interrupt");
        }
        System.out.println("Ending Wax2 On task");
    }
}//WaxOn2

/*class WaxOn implements Runnable {
    private Car car;

    public WaxOn(Car c) {
        car = c;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.print("Wax On! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitForBuffing();
            }
        } catch (InterruptedException e) {
            System.out.print("Exiting via interrupt");
        }
        System.out.print("Ending Wax On task");
    }
}*/

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
            System.out.println("Wax Off exiting via interrupt");
        }
        System.out.println("Ending Wax Off task");
    }
}

public class MyWaxOMatic {
    public static void main(String[] args) throws Exception{
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();

        exec.execute(new WaxOff(car));
        exec.execute(new WaxOn1(car));
        exec.execute(new WaxOn2(car));

        TimeUnit.SECONDS.sleep(5); // Run for a while...
        exec.shutdownNow(); // Interrupt all tasks
    }
}
