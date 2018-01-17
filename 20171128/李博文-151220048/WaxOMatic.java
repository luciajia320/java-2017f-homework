import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Car {
    private boolean waxOn = false;
    private boolean isWaxing = false;
    public synchronized boolean waitingForOthersWaxing() {
        if(isWaxing || waxOn)
            return false;
        else {
            isWaxing = true;
            return true;
        }
    }
    public synchronized void waxed() {
        waxOn = true;
        isWaxing = false;
        notifyAll();
    }
    public synchronized void buffed() {
        waxOn = false;
        notifyAll();
    }
    public synchronized void waitingForWaxing() throws InterruptedException {
        while(!waxOn) {
            wait();
        }
    }
    public synchronized void waitingForBuffing() throws InterruptedException {
        while(waxOn) {
            wait();
        }
    }
}
class WaxOn implements Runnable {
    private Car car;
    private static int COUNT = 1;
    private int id = COUNT++;
    public WaxOn(Car car) {
        this.car = car;
    }
    @Override
    public void run(){
        try {
            while (!Thread.interrupted()) {
                if(car.waitingForOthersWaxing()) {
                    System.out.println("WaxOn" + this.id + ": Wax On!");
                    TimeUnit.MILLISECONDS.sleep(200);
                    car.waxed();
                    car.waitingForBuffing();
                }
            }
        }catch (InterruptedException e) {
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending Wax on task");

    }
}

class WaxOff implements Runnable {
    private Car car;
    public WaxOff(Car car) {
        this.car = car;
    }
    @Override
    public void run(){
        try {
            while (!Thread.interrupted()) {
                car.waitingForWaxing();
                System.out.println("Wax Off!");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via Interrupt");
        }
        System.out.println("Ending Was Off task");
    }
}
public class WaxOMatic {
    public static void main(String[] args) throws Exception{
        Car car = new Car();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new WaxOff(car));
        executorService.execute(new WaxOn(car));
        executorService.execute(new WaxOn(car));
        TimeUnit.SECONDS.sleep(3);
        executorService.shutdownNow();
    }
}
