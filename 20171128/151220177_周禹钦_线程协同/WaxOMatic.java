/**
 * Created by qin on 18.1.1.
 */
import java.util.concurrent.*;
class Car {
    private int[] ready=new int[2];

    public synchronized boolean getReady(int i){
        if(ready[i]==1)
            return true;
        else return false;
    }
    public synchronized void readyToWax(int i) throws InterruptedException{
        if(ready[0]==1||ready[1]==1)
            wait();
        else ready[i]=1;
        //notify();
    }
    public Car(){
        ready[0]=0;
        ready[1]=0;
    }

    public synchronized void notifySelf() {
        notify();
    }

    public synchronized void buffed() {
        ready[0] = 0; // Ready for another coat of wax
        ready[1] = 0;
        notify();
    }


    public synchronized void waitForWaxing()
            throws InterruptedException {
        while (ready[0] == 0 && ready[1] == 0)
            wait();
    }

    public synchronized void waitForBuffing()
            throws InterruptedException {
        while (ready[1] == 1 || ready[0]== 1)
            wait();
    }
}

class WaxOn1 implements Runnable {
    private Car car;

    public WaxOn1(Car c) {
        car = c;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.readyToWax(0);
                if(!car.getReady(0))
                    continue;
                else{
                System.out.print("Wax On1! ");
                car.notifySelf();
                TimeUnit.MILLISECONDS.sleep(200);

                car.waitForBuffing();}
            }
        } catch (InterruptedException e) {
            System.out.print("Exiting via interrupt");
        }
        System.out.print("Ending Wax On task");
    }
}

class WaxOn2 implements Runnable {
    private Car car;

    public WaxOn2(Car c) {
        car = c;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.readyToWax(1);
                if(!car.getReady(1))
                    continue;
                else{
                System.out.print("Wax On2! ");
                car.notifySelf();
                TimeUnit.MILLISECONDS.sleep(200);
                car.waitForBuffing();}
            }
        } catch (InterruptedException e) {
            System.out.print("Exiting via interrupt");
        }
        System.out.print("Ending Wax On task");
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
                car.buffed();
                TimeUnit.MILLISECONDS.sleep(200);
            }
        } catch (InterruptedException e) {
            System.out.print("Exiting via interrupt");
        }
        System.out.print("Ending Wax Off task");
    }
}

public class WaxOMatic {
    public static void main(String[] args) throws Exception {
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));
        exec.execute(new WaxOn1(car));
        exec.execute(new WaxOn2(car));
        TimeUnit.SECONDS.sleep(5); // Run for a while...
        exec.shutdownNow(); // Interrupt all tasks
    }
}