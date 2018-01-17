import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class waxThread extends Thread {
    private final Car car;
    private int id;


    public waxThread(int id, Car car) {
        this.car = car;
        this.id = id;

    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            waxCar();
        }
    }

    private void waxCar(){
        synchronized (car){
            try{
                while(car.isWaxed()){
                    car.wait();
                }
                TimeUnit.MILLISECONDS.sleep(200);
                car.wax();
                System.out.println("WaxOn"+id+":Wax On!");
                car.notifyAll();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

}
