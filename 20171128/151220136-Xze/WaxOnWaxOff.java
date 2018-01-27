import java.util.concurrent.*;

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
    private Integer num;

    public WaxOn(Car car, Integer num){
        this.car = car;
        this.num = num;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (car) {
                    car.waitForBuffing();
                    System.out.print(this + ": Wax On!\n");
                    TimeUnit.MILLISECONDS.sleep(200);
                    car.waxed();
                }
            }
        } catch (InterruptedException e) {
            System.out.print("Exiting via interrpt");
        }
        System.out.print("Ending Wax On task");
    }

    @Override
    public String toString() {
        return "WaxOn" + this.num;
    }
}

class WaxOff implements Runnable {
    private Car car;

    public WaxOff(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                car.waitForWaxing();
                System.out.print("Wax Off!\n");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            }
        } catch (InterruptedException e){
            System.out.print("Exiting via interrupt");
        }
        System.out.print("Ending Wax Off task");
    }
}

public class WaxOnWaxOff {
    public static void main(String[] args) throws Exception{
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));
        exec.execute(new WaxOn(car, 1));
        exec.execute(new WaxOn(car, 2));
        TimeUnit.MILLISECONDS.sleep(5);
        exec.shutdown();
    }
}
