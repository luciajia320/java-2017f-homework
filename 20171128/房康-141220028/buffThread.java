import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class buffThread extends Thread {
    private final Car car;

    public buffThread(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        while (true) {
            buffCar();
        }
    }

    public void buffCar(){
       synchronized (car){
           try{
               while(!car.isWaxed()){
                   car.wait();
               }
               TimeUnit.MILLISECONDS.sleep(200);
               car.buff();
               System.out.println("Wax Off!");
               car.notifyAll();
           }catch (InterruptedException e){
               e.printStackTrace();
           }
       }
    }

}
