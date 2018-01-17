import java.util.concurrent.TimeUnit;

public class WaxOn implements Runnable {

    final int id;
    private Car car;
    public WaxOn(Car c,int id) {
        this.id=id;
        car = c;
    }
    public  void run() {
        synchronized(car)
        {
            try {
                while (!Thread.interrupted()) {
                    System.out.println("WaxOn"+id+": Wax On! \n");
                    TimeUnit.MILLISECONDS.sleep(200);
                    car.waxed();
                    car.waitForBuffing();
                }
            } catch (InterruptedException e) {
                System.out.println("Exiting via interrupt");
            }
            System.out.println("Ending Wax On task");
        }
    }
}