package concurrency.waxomatic;

import java.util.concurrent.*;

import java.util.Random;

class Car {
    private boolean waxOn = false;
    private int randomWax;
    
    public void setRandom() {
    	randomWax=new Random().nextInt();
    }

    public synchronized void waxed() {

        waxOn = true; // Ready to buff
        notifyAll();

    }

    public synchronized void buffed() {
    	setRandom();
        waxOn = false; // Ready for another coat of wax
        notifyAll();
    }

    public synchronized void waitForWaxing()
            throws InterruptedException {
        while (waxOn == false)
            wait();
    }

    public synchronized void waitForBuffing1()
            throws InterruptedException {
        while (waxOn == true || randomWax%2==1)
            wait();
    }
    
    public synchronized void waitForBuffing2()
            throws InterruptedException {
        while (waxOn == true || randomWax%2==0)
            wait();
    }
}

class WaxOn1 implements Runnable {
    private int whitch;
    private Car car;

    public WaxOn1(int n, Car c) {
    	whitch=n;
        car = c;
    }

    public  void run() {
        synchronized(car)
        {
            try {
                while (!Thread.interrupted()) {
                    System.out.println("WaxOn"+whitch+": Wax On! \n");
                    TimeUnit.MILLISECONDS.sleep(200);
                    car.waxed();
                    if(whitch==1)
                    	car.waitForBuffing1();
                    else
                    	car.waitForBuffing2();
                }
            } catch (InterruptedException e) {
                System.out.println("Exiting via interrupt");
            }
            System.out.println("Ending Wax On task");
        }
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
                System.out.println("Wax Off! \n");
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
        exec.execute(new WaxOn1(1,car));
        exec.execute(new WaxOn1(2,car));
        TimeUnit.SECONDS.sleep(10); // Run for a while...
        exec.shutdownNow(); // Interrupt all tasks
    }
}