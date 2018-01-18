import java.util.concurrent.*;

import static net.mindview.util.Print.*;

class Car {
    private boolean waxOn = false;
    private boolean waxing = false;
    public synchronized void waxed() {
        //waxing=true;
        waxOn = true; // Ready to buff
        notifyAll();
    }

    public synchronized void buffed() {
        waxOn = false; // Ready for another coat of wax
        waxing=false;
        notifyAll();
    }

    public synchronized void waitForWaxing()
            throws InterruptedException {
        while (waxOn == false)
            wait();
    }
    public synchronized void waitForOtherWaxing()
            throws InterruptedException {
        while (waxing)  //waiting when someone else is waxing
            wait();
        waxing=true;  //解除锁之前先给wax赋值 避免重复wax
    }

    public synchronized void waitForBuffing()
            throws InterruptedException {
        while (waxOn == true)
            wait();
    }
}

class WaxOn implements Runnable {
    private Car car;
    private int id;
    public WaxOn(Car c,int id) {
        car = c;
        this.id=id;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.waitForOtherWaxing();
                printnb(this.getClass().getSimpleName()+ Integer.toString(id)+": Wax On! \n");
                TimeUnit.MILLISECONDS.sleep(200);//锁释放了
                car.waxed();
                car.waitForBuffing();
            }
        } catch (InterruptedException e) {
            print("Exiting via interrupt");
        }
        print("Ending Wax On task");
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
                car.waitForWaxing();// waxon==false wait
                printnb("Wax Off! \n");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            }
        } catch (InterruptedException e) {
            print("Exiting via interrupt");
        }
        print("Ending Wax Off task");
    }
}

public class WaxOMatic2 {
    public static void main(String[] args) throws Exception {
        //同一辆car,两个人，一个涂蜡一个抛光
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        WaxOn waxOn1=new WaxOn(car,1);
        WaxOn waxOn2=new WaxOn(car,2);
        exec.execute(waxOn1);
        exec.execute(waxOn2);
        exec.execute(new WaxOff(car));
        TimeUnit.SECONDS.sleep(5); // Run for a while... 工作一段时间
        exec.shutdownNow(); // Interrupt all tasks
    }
}
