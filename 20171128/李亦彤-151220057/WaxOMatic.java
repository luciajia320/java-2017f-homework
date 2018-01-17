
import java.util.concurrent.*;



class Car {
    private boolean waxOn = false;
    private boolean waxing = false;

    public synchronized void waxed() {
            waxOn = true;// Ready to buff
            waxing = false;
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
    public synchronized void waitForOtherWaxing()//若车正在被上蜡或已被上蜡则等待
            throws InterruptedException {
        while (waxing == true||waxOn==true)
            wait();
        waxing=true;
    }

}

class WaxOn implements Runnable {
    private Car car;
    private String name;
    public WaxOn(Car c,String name1) {
        car = c;
        name=name1;
    }

    public void run() {
        try {
            while (!Thread.interrupted()){
                    car.waitForOtherWaxing();
                    System.out.println(name + ":Wax On! \n");
                    TimeUnit.MILLISECONDS.sleep(200);
                    car.waxed();
                    Thread.yield();
                    car.waitForBuffing();

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
        exec.execute(new WaxOn(car,"WaxOn1"));
        exec.execute(new WaxOn(car,"WaxOn2"));

        TimeUnit.SECONDS.sleep(5); // Run for a while...
        exec.shutdownNow(); // Interrupt all tasks
    }
}