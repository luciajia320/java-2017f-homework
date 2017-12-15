//package concurrency.waxomatic;

import java.util.concurrent.*;
//import static net.mindview.util.Print.*;
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

    boolean isWaxed(){return waxOn;}
}

class WaxOn1 implements Runnable {
    private Car car;

    public WaxOn1(Car c) {
        car = c;
    }

    public void run() {

            try {
                while (!Thread.interrupted()) {
                    synchronized (car) {
                        if(!car.isWaxed()) {
                            System.out.println("WaxOn1:Wax On! ");
                            TimeUnit.MILLISECONDS.sleep(200);
                            car.waxed();
                        }
                    }
                    car.waitForBuffing();
                }
            } catch (InterruptedException e) {
                System.out.println("Exiting via interrupt");
            }
            System.out.println("Ending Wax On1 task");
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
                    synchronized (car) {
                        if(!car.isWaxed()) {
                            System.out.println("WaxOn2:Wax On! ");
                            TimeUnit.MILLISECONDS.sleep(200);
                            car.waxed();
                        }
                    }
                    car.waitForBuffing();
                }
            } catch (InterruptedException e) {
                System.out.println("Exiting via interrupt");
            }
            System.out.println("Ending Wax On2 task");
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
        exec.execute(new WaxOn1(car));
        exec.execute(new WaxOn2(car));
        TimeUnit.SECONDS.sleep(5); // Run for a while...
        exec.shutdownNow(); // Interrupt all tasks
    }
}

