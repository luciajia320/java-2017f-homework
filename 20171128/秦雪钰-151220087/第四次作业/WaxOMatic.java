//package concurrency.waxomatic;

import java.util.Random;
import java.util.concurrent.*;

//import static net.mindview.util.Print.*;



//import static net.mindview.util.Print.*;

class Car {
    private boolean waxOn = false;

    public synchronized void waxed(Car car) {

        waxOn = true; // Ready to buff
//        while(waxOn==true)
//            car.notify();
        notifyAll();

    }

    public synchronized void buffed(Car car) {
        waxOn = false; // Ready for another coat of wax
        Random random=new Random();
        if(random.nextBoolean())
        {
            car.notify();
            //notifyAll();
        }
        else
            notifyAll();
    }

    public synchronized void waitForWaxing()
            throws InterruptedException {
        Random random=new Random();
        while (waxOn == false)
            wait();
//        if(waxOn==true)
//            Thread.sleep(random.nextInt(40));
    }

    public synchronized void waitForBuffing()
            throws InterruptedException {
        while (waxOn == true)
            wait();
    }
}

class WaxOn1 implements Runnable {
    private Car car;

    public WaxOn1(Car c) {
        car = c;
    }

    public  void run() {
        synchronized(car)
        {
            try {
                while (!Thread.interrupted()) {
                    System.out.println("WaxOn1: Wax On! \n");
                    TimeUnit.MILLISECONDS.sleep(200);
                    car.waxed(car);
                    car.waitForBuffing();
                }
            } catch (InterruptedException e) {
                System.out.println("Exiting via interrupt");
            }
            System.out.println("Ending Wax On task");
        }
    }
}
class WaxOn2 implements Runnable {
    private Car car;

    public WaxOn2(Car c) {
        car = c;
    }

    public synchronized void run() {
        synchronized(car)
        {
            try {
                while (!Thread.interrupted()) {
                    System.out.println("WaxOn2: Wax On! \n");
                    TimeUnit.MILLISECONDS.sleep(200);
                    car.waxed(car);
                    car.waitForBuffing();
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
                car.buffed(car);
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
        TimeUnit.SECONDS.sleep(10); // Run for a while...
        exec.shutdownNow(); // Interrupt all tasks
    }
}