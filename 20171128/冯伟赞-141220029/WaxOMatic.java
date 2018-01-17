import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Car {
    private Boolean waxOned = false;

    public void waxed() {
        waxOned = true;
    }

    public void buffed() {
        waxOned = false;
    }

    public Boolean isWaxOned() {
        return waxOned;
    }


}

class WaxOn implements Runnable {
    private Car car;

    public WaxOn(Car c) {
        car = c;
    }

    public void run() {
        while (!Thread.interrupted()) {
            synchronized (car) {
                try {
                    while (car.isWaxOned()) {
                        car.wait();
                    }
                    TimeUnit.MILLISECONDS.sleep(200);
                    car.waxed();
                    System.out.println(Thread.currentThread().getName() + ": Wax On!");
                    car.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class WaxOff implements Runnable {
    private Car car;

    public WaxOff(Car c) {
        car = c;
    }

    public void run() {
        while (!Thread.interrupted()) {
            synchronized (car) {
                try {
                    while (!car.isWaxOned()) {
                        car.wait();
                    }
                    Thread.sleep(200);
                    car.buffed();
                    System.out.println("Wax off!");
                    car.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

public class WaxOMatic {
    public static void main(String[] args) throws Exception {
        Car car = new Car();
        Thread waxon1 = new Thread(new WaxOn(car));
        Thread waxon2 = new Thread(new WaxOn(car));
        Thread waxoff = new Thread(new WaxOff(car));
        waxon1.setName("WaxOn1");
        waxon2.setName("WaxOn2");
        waxoff.setName("WaxOff");
        waxoff.start();
        waxon1.start();
        waxon2.start();
    }
}