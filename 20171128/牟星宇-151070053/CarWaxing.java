import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CarWaxing {

    public static void main(String[] args) {
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        WaxOn waxOn1 = new WaxOn(car, 1);
        WaxOn waxOn2 = new WaxOn(car, 2);
        WaxOff waxOff1 = new WaxOff(car, 1);
        WaxOff waxOff2 = new WaxOff(car, 2);
        exec.execute(waxOff1);
        exec.execute(waxOff2);
        exec.execute(waxOn1);
        exec.execute(waxOn2);

        try{
            TimeUnit.SECONDS.sleep(5); // Run for a while...
        }catch(InterruptedException e){
            System.out.println("Exiting via interrupt");
        }
        exec.shutdownNow(); // Interrupt all tasks
    }
}
