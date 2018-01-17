
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


class Car {
    private boolean waxOn = false;
    private boolean waxnum=true;//waxon block

    public synchronized void waxed() {
        waxOn = true; // Ready to buff
  //      waxnum=false; //notReady to wax
       notifyAll();
    }
    
    public synchronized void waxlock() {
    	waxnum=false;
    //	notifyAll();
    }

    public synchronized void buffed() {
        waxOn = false; // Ready for another coat of wax
        //notifyAll();
      //  waxnum=true; //Ready for wax
    //    notifyAll();
    }
    
    public synchronized void waxunlock() {
    	waxnum=true;
    	notifyAll();
    }

    public synchronized void waitForWaxing()
            throws InterruptedException {
        while (waxOn == false)
            wait();
        
    }

    public synchronized void waitForBuffing()
            throws InterruptedException {
        while (waxOn == true||waxnum==false)
            wait();
    }
}

class WaxOn implements Runnable {
    private Car car;
    private int id;//identify waxon programs
    public static int mun=1;
    public WaxOn(Car c,int i) {
        car = c;
        id=i;
      /*  if(id!=1){
        	try{
        	 TimeUnit.MILLISECONDS.sleep(200);
        	 }
        	catch (InterruptedException e) {
             	System.out.println("Exiting via interrupt");
        	}
        }*/
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
            	car.waitForBuffing();
            	car.waxlock();
                System.out.println("WaxOn"+id+":Wax On! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                //TimeUnit.MILLISECONDS.sleep(200);
              //  car.waitForBuffing();
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
                car.waxunlock();
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
        exec.execute(new WaxOn(car,1));
        exec.execute(new WaxOn(car,2));
        TimeUnit.SECONDS.sleep(5); // Run for a while...
        exec.shutdownNow(); // Interrupt all tasks
    }
}

