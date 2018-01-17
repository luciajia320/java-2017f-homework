//package concurrency.waxomatic;
//import static net.mindview.util.Print.*;
public class Car {
    private boolean waxOn = false;   // true 表示已经有车在等待buffer  false表示新car可以wax
    private boolean bufferOff = true;  // true 表示新car可以wax  false表示前一车在等待buffer

    public synchronized void ifExitCar()throws InterruptedException {
        while(!bufferOff)
            wait();
        bufferOff=false;
    }
    public synchronized void waxed() {
           waxOn = true; // Ready to buff
           bufferOff=false;
        //   bufferOff=true;
           notifyAll();
    }

    public synchronized void buffed() {
           waxOn = false; // Ready for another coat of wax
           bufferOff=true;
           notifyAll();
    }

    public synchronized void waitForWaxing() throws InterruptedException {
           while (waxOn == false)//||bufferOff)
                   wait();
    }

    public synchronized void waitForBuffing() throws InterruptedException {
           while (waxOn == true)//||!bufferOff)
                   wait();
    }
}
