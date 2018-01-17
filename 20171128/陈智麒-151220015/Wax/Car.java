package Wax;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Car {
    private boolean waxOn = false;
    private boolean only = true;
    public synchronized void waxed(){
        waxOn = true;
        only = true;
        notifyAll();
    }
    public synchronized void buffed(){
        waxOn = false;
        only = true;
        notifyAll();
    }
    public synchronized void waitForWaxing() throws InterruptedException {
        while(waxOn == false || only == false)
            wait();
        waxOn = false;
        only = false;
    }
    public synchronized void waitForBuffing() throws InterruptedException {
        while(waxOn == true || only == false)
            wait();
        waxOn = true;
        only = false;
    }
}

class WaxOn implements Runnable{
    private int no;
    private Car car;
    public WaxOn(int no, Car car){
        this.no = no;
        this.car = car;
    }
    @Override
    public void run() {
        try{
            while(!Thread.interrupted()) {
                //should be: car.waxOn == false, car.only == true
                car.waitForBuffing();
                //guarantee: car.waxOn == true, car.only == false
                System.out.println("WaxOn"+no+":Wax On!");
                TimeUnit.SECONDS.sleep(2);
                car.waxed();
                //guarantee: car.waxOn == true, car.only == true
            }
        }catch(InterruptedException e){
            System.out.println(e);
        }
    }
}

class WaxOff implements Runnable{
    private Car car;
    public WaxOff(Car car){
        this.car = car;
    }
    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                    //should be: car.waxOn = true
                    car.waitForWaxing();
                    //guarantee: car.waxOn == false
                    System.out.println("Wax Off!");
                    TimeUnit.SECONDS.sleep(2);
                    car.buffed();
                    //car.waxOn == false, car.only = true
            }
        }catch(InterruptedException e){
            System.out.println(e);
        }
    }
}

class WaxOMatic{
    public static void main(String[] args) {
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOn(1,car));
        exec.execute(new WaxOn(2,car));
        exec.execute(new WaxOff(car));
    }
}