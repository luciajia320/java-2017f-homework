
public class Car {
    private boolean waxOn = false;
    private boolean mutex4WaxOn = true; //����mutexʹ����waxOn����
    
    
    public synchronized void waxed() {
    	waxOn = true; // Ready to buff
    	mutex4WaxOn = true;     //��waxOn�Ѿ�������󣬽�mutex��Ϊ1
        notifyAll();
    }

    public synchronized void buffed() {
        waxOn = false; // Ready for another coat of wax
        notifyAll();
    }
     
    public synchronized void wait4anotherWaxOn() throws InterruptedException {
        while (waxOn == true || mutex4WaxOn == false)
            wait();
        mutex4WaxOn = false;     //��waxOn���ڹ���ʱ��mutex = 0;
    }
    
    public synchronized void waitForWaxing() throws InterruptedException {
        while (waxOn == false)
            wait();
    }

    public synchronized void waitForBuffing() throws InterruptedException {
        while (waxOn == true)
            wait();
    }
}
