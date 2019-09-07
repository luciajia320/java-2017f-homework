/*�γ���վ��ʾ������waxomatic�д�����һ��WaxOn�̺߳�һ��WaxOff�̣߳���ͨ��wait()��notify()ʹ��Эͬ����
����Car�����waxOn==trueʱWaxOff�߳̿�ʼ��������waxOn==faseʱWaxOn�߳̿�ʼ��������˽��棬�Դ�������

Ҫ�󣺽��öγ��������ӿ���һ��WaxOn�̣߳�������ʱ��������WaxOn�̣߳��ֱ�����ΪWaxOn1 �� WanOn2����һ��WaxOff�̣߳�
��waxOn==trueʱWaxOff��������waxOn==falseʱĳһ����������ĳ��ȷ���ģ�WaxOn�̹߳���������ԭ�С�Wax On! \n��
����ַ���ǰ�������߳����ƣ����ֱ������WaxOn1: Wax On! \n���͡�WaxOn2: Wax On! \n������*/
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
