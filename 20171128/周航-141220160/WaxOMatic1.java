import java.util.concurrent.*;

//import static net.mindview.util.Print.*;

class Car {
    private boolean waxOn = false;
    private volatile boolean flag=false;
    public synchronized void waxed() {
        waxOn = true; // Ready to buff
        flag=false;
        notifyAll();
    }

    public synchronized void buffed() {
        waxOn = false; // Ready for another coat of wax
        notifyAll();
    }

    public synchronized boolean waxing(){
        if(!flag){
            flag=true;
            return true;
        }
        else return false;
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
    private int num;

    public WaxOn(Car c) {
        car = c;
    }
    public WaxOn(Car c,int n){
        car=c;
        num=n;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                if(car.waxing()){
                    System.out.print("WaxOn"+num+":Wax ON!\n");
                    TimeUnit.MILLISECONDS.sleep(200);
                    car.waxed();
                }
                else
                    car.waitForWaxing();
                car.waitForBuffing();
            }
        } catch (InterruptedException e) {
            System.out.print("Exiting via interrupt\n");
        }
        System.out.print("Ending Wax On task:"+num+"\n");
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
                System.out.print("Wax Off! \n");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            }
        } catch (InterruptedException e) {
            System.out.print("Exiting via interrupt\n");
        }
        System.out.print("Ending Wax Off task\n");
    }
}

public class WaxOMatic1 {
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