//: concurrency/waxomatic/WaxOMatic.java
// Basic task cooperation.
package concurrency.waxomatic;
import java.util.concurrent.*;

class Car {
    private boolean waxOn = false;
    private boolean isWait = false;
    public synchronized void waxed() {
        waxOn = true; // Ready to buff
        isWait = true;
        notifyAll();
    }
    public synchronized void buffed() {
        waxOn = false; // Ready for another coat of wax
        isWait = false;
        notifyAll();
    }
    public synchronized void waitForAnother()
            throws InterruptedException {
        while (isWait)
            wait();
        isWait = true;
    }
    public synchronized void waitForWaxing()
            throws InterruptedException {
        while (!waxOn)
            wait();
    }
    public synchronized void waitForBuffing()
            throws InterruptedException {
        while(waxOn)
            wait();
    }
}

class WaxOn implements Runnable {
    private Car car;
    private int i;
    public WaxOn(Car c, int i) { car = c; this.i = i; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                car.waitForAnother();
                System.out.print("WaxOn" + i + ": Wax On! \n");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitForBuffing();
            }
        } catch(InterruptedException e) {
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending Wax On task");
    }
}

class WaxOff implements Runnable {
    private Car car;
    public WaxOff(Car c) { car = c; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                car.waitForWaxing();
                System.out.print("Wax Off! \n");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            }
        } catch(InterruptedException e) {
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
        TimeUnit.SECONDS.sleep(5); // Run for a while...
        exec.shutdownNow(); // Interrupt all tasks
    }
}
/* Output:
WaxOn1: Wax On! 
Wax Off! 
WaxOn2: Wax On! 
Wax Off! 
WaxOn1: Wax On! 
Wax Off! 
WaxOn2: Wax On! 
Wax Off! 
WaxOn1: Wax On! 
Wax Off! 
WaxOn2: Wax On! 
Wax Off! 
WaxOn1: Wax On! 
Wax Off! 
WaxOn2: Wax On! 
Wax Off! 
WaxOn1: Wax On! 
Wax Off! 
WaxOn2: Wax On! 
Wax Off! 
WaxOn1: Wax On! 
Wax Off! 
WaxOn2: Wax On! 
Wax Off! 
WaxOn1: Wax On! 
Exiting via interrupt
Ending Wax On task
Exiting via interrupt
Exiting via interrupt
Ending Wax On task
Ending Wax Off task
*///:~
