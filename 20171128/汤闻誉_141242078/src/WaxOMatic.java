import java.util.concurrent.*;

class Car {
    private boolean waxOn = false;
    private boolean waxOffAndWaxOnDecided =false;    //是否未打蜡且选定打蜡的机器
    //初始化条件和以下四个方法保证waxOnDecided为false时WaxOn一定也是false
    public synchronized void waxed() {
        waxOn = true; // Ready to buff
        notifyAll();
    }

    public synchronized void buffed() {
        waxOn = false; // Ready for another coat of wax
        waxOffAndWaxOnDecided =false;
        notifyAll();
    }


    public synchronized void waitForWaxing()
            throws InterruptedException {
        while (waxOn == false)
            wait();
    }
    //Now useless
    /*
    public synchronized void waitForBuffing()
            throws InterruptedException {
        while (waxOn == true)
            wait();
    }
*/
    public synchronized void applyWorking() throws InterruptedException{
        while(waxOffAndWaxOnDecided) wait();
        waxOffAndWaxOnDecided =true;
    }
}

class WaxOn implements Runnable {
    private Car car;
    private int WaxMachineNo;

    public WaxOn(Car c,int No) {
        car = c;
        WaxMachineNo=No;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
   //             car.waitForBuffing();
                car.applyWorking();
                System.out.println("WaxOn"+WaxMachineNo+": Wax On! ");
                TimeUnit.MILLISECONDS.sleep(17);
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
                TimeUnit.MILLISECONDS.sleep(19);
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
        exec.execute(new WaxOn(car,1));
        exec.execute(new WaxOn(car,2));
        TimeUnit.SECONDS.sleep(20); // Run for a while...
        exec.shutdownNow(); // Interrupt all tasks
    }
}