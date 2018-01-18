
public class Car {
    private boolean waxOn = false;
    private boolean mutex4WaxOn = true; //加入mutex使两个waxOn互斥
    
    
    public synchronized void waxed() {
    	waxOn = true; // Ready to buff
    	mutex4WaxOn = true;     //当waxOn已经工作完后，将mutex设为1
        notifyAll();
    }

    public synchronized void buffed() {
        waxOn = false; // Ready for another coat of wax
        notifyAll();
    }
     
    public synchronized void wait4anotherWaxOn() throws InterruptedException {
        while (waxOn == true || mutex4WaxOn == false)
            wait();
        mutex4WaxOn = false;     //当waxOn正在工作时，mutex = 0;
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
