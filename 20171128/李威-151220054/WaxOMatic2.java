
import java.util.concurrent.*;

class myCar {
	private boolean waxing = false;//the variable means 	is (or not) waxing now
    private boolean waxOn = false;
   
    
    public synchronized void ableToWaxOn() throws InterruptedException {
    	 while (waxOn == true||waxing==true)
             wait();
    	 waxing=true;
        
    }
    public synchronized void waxed() {
        waxOn = true; // Ready to buff
        waxing=false;
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
}

class myWaxOn implements Runnable {
    private myCar car;
    int num;
    public myWaxOn(myCar c,int n) {
        car = c;
        num=n;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
            	car.ableToWaxOn();
            	System.out.println("WaxOn"+num+":Wax On!");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitForBuffing();}
            
        } catch (InterruptedException e) {
        	System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending Wax On task");
    }
}

class myWaxOff implements Runnable {
    private myCar car;

    public myWaxOff(myCar c) {
        car = c;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.waitForWaxing();
                System.out.println("Wax Off!");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending Wax Off task");
    }
}

public class WaxOMatic2 {
    public static void main(String[] args) throws Exception {
        myCar car = new myCar();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new myWaxOff(car));
        exec.execute(new myWaxOn(car,1));
        exec.execute(new myWaxOn(car,2));
        TimeUnit.SECONDS.sleep(5); // Run for a while...
        exec.shutdownNow(); // Interrupt all tasks
    }
}