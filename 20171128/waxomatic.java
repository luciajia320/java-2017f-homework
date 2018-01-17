/*课程网站上示例代码waxomatic中创建了一个WaxOn线程和一个WaxOff线程，并通过wait()和notify()使其协同工作
（在Car对象的waxOn==true时WaxOff线程开始工作，当waxOn==fase时WaxOn线程开始工作，如此交替，以此往复）

要求：将该段程序中增加开启一个WaxOn线程，即运行时存在两个WaxOn线程（分别命名为WaxOn1 和 WanOn2）和一个WaxOff线程，
当waxOn==true时WaxOff工作，当waxOn==false时某一个（但不是某个确定的）WaxOn线程工作，并在原有“Wax On! \n”
输出字符串前面增加线程名称（即分别输出“WaxOn1: Wax On! \n”和”WaxOn2: Wax On! \n“）。*/
import java.util.concurrent.*;

class Car {
    private boolean waxOn1 = false;
    private boolean waxOn2 = false;

    public synchronized void waxed(int i) {
        if(i == 1)
        	waxOn1 = true; // Ready to buff
        if(i == 2)
        	waxOn2 = true;
        notifyAll();
    }
    
    public synchronized void buffed() {
        waxOn1 = false; // Ready for another coat of wax
        waxOn2 = false;
        notifyAll();
    }

    public synchronized void waitForWaxing()
            throws InterruptedException {
        while (waxOn1 == false && waxOn2 == false)
            wait();
    }

    public synchronized void waitForBuffing()
            throws InterruptedException {
        while (waxOn1 == true || waxOn2 == true)
            wait();
    }

}

class WaxOn implements Runnable {
    private Car car;
    private Integer id;
    
    public WaxOn(Car c, int i) {
        car = c;
        id = i;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println("WaxOn"+this.id+" : Wax On! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed(id);
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
                System.out.println("Wax Off! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending Wax Off task");
    }
}

public class waxomatic {
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
