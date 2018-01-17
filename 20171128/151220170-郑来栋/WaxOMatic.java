import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Car {
    private boolean waxOn = false;

    private boolean isWaxing = false;

    public synchronized void waxed() {

        waxOn = true;
        isWaxing = false;
        notifyAll();
    }

    public synchronized void wantToWax()
            throws InterruptedException
    {
        /*有人正在打蜡或者已经上蜡则等待*/
        while (isWaxing || waxOn)
            wait();
        isWaxing = true;
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
                    car.wantToWax();
                    System.out.println("WaxOn1: Wax On! \n");
                    TimeUnit.MILLISECONDS.sleep(200);
                    car.waxed();
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
                    car.wantToWax();
                    System.out.println("WaxOn2: Wax On! \n");
                    TimeUnit.MILLISECONDS.sleep(200);
                    car.waxed();
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
        TimeUnit.SECONDS.sleep(10);
        exec.shutdownNow(); // Interrupt all tasks
    }
}