import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Car {

    private Boolean waxed;

    public Car(){
        this.waxed = false;
    }

    public synchronized void wax(){
        this.waxed = true;
    }

    public synchronized void buff(){
        this.waxed = false;
    }

    public Boolean isWaxed(){
        return this.waxed;
    }

}
