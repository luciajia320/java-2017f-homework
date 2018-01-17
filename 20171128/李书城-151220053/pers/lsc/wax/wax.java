package pers.lsc.wax;
import  java.util.concurrent.*;

class Car {
    private boolean waxOn = false;

    public synchronized void waxed() {

        waxOn = true; // Ready to buff
        notifyAll();
    }

    public synchronized void buffed() {
        waxOn = false; // Ready for another coat of wax
        notify();
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
    private int num ;

    public WaxOn(Car c,int n) {
        car = c;
        num = n;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (WaxOn.class) {
                    System.out.print("Wax On"+ num + ": Wax On!\n ");
                    TimeUnit.MILLISECONDS.sleep(200);
                    car.waxed();
                    car.waitForBuffing();
                }
            }
        } catch (InterruptedException e) {
            System.out.print("Exiting via interrupt\n");
        }
        System.out.print("Ending Wax On"+ num+" task\n");
    }
}
/*
class WaxOn2 implements Runnable {
    private Car car;
    int num;


    public WaxOn2(Car c) {
        car = c;
        num = 0;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
               if(num++ != 0)
                   System.out.print("Wax On2: Wax On!\n ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitForBuffing();
            }
        } catch (InterruptedException e) {
            System.out.print("Exiting via interrupt\n");
        }
        System.out.print("Ending Wax On2 task\n");
    }
}
*/

class WaxOff implements Runnable {
    private Car car;

    public WaxOff(Car c) {
        car = c;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.waitForWaxing();
                System.out.print("Wax Off!\n ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            }
        } catch (InterruptedException e) {
            System.out.print("Exiting via interrupt\n");
        }
        System.out.print("Ending Wax Off task\n");
    }
}

class WaxOMatic {
    public static void main(String[] args) throws Exception {
        Car car = new Car();
        ExecutorService exec = Executors.newFixedThreadPool(3);
        exec.execute(new WaxOff(car));
        exec.execute(new WaxOn(car,1));
        exec.execute((new WaxOn(car,2)));
        TimeUnit.SECONDS.sleep(5); // Run for a while...
        exec.shutdownNow(); // Interrupt all tasks
    }
}