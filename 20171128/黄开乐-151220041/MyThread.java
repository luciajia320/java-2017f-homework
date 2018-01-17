//package concurrency.waxomatic;

import java.util.concurrent.*;
import java.util.Random;
//import static net.mindview.util.Print.*;

class Car {
    private boolean waxOn = false;
    private int wax_no = 1;

    public synchronized void waxed() {
        waxOn = true; // Ready to buff
        notifyAll();
    }

    public synchronized void buffed() {
        waxOn = false; // Ready for another coat of wax
        Random r = new Random();
        wax_no = r.nextInt();
        wax_no = (wax_no % 2)+1;
        while(wax_no < 1 || wax_no > 2) {
        	wax_no = r.nextInt();
            wax_no = (wax_no % 2)+1;
        }
        //生成随机数，表示下一次运行的waxon的序号
        notifyAll();
    }

    public synchronized void waitForWaxing()
            throws InterruptedException {
        while (waxOn == false)
            wait();
    }

    public synchronized void waitForBuffing(int no)
            throws InterruptedException {
        while (waxOn == true || wax_no != no)
            wait();
    }
}

class WaxOn implements Runnable {
    private Car car;
    private int number;

    public WaxOn(Car c, int num) {
        car = c; number = num;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
            	car.waitForBuffing(number);
                System.out.println("WaxOn" + number + ":Wax On" +" ! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
            }
        } catch (InterruptedException e) {
        	System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending Wax On task");
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
        exec.execute(new WaxOn(car, 1));
        exec.execute(new WaxOn(car, 2));
        TimeUnit.SECONDS.sleep(5); // Run for a while...
        exec.shutdownNow(); // Interrupt all tasks
    }
}