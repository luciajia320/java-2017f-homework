import java.util.concurrent.*;

public class WaxOn implements Runnable {
    private Car car;
    private int number;

    public WaxOn(Car c, int number) {

        this.number = number;
        car = c;
    }

    public void run() {
        synchronized (car){
            try {
                while (!Thread.interrupted()) {
                    car.waitForBuffing();
                    TimeUnit.MILLISECONDS.sleep(200);
                    System.out.println("WaxOn" + this.number + ":Wax On! ");
                    car.waxed();
                }
            } catch (InterruptedException e) {
                System.out.println("Exiting via interrupt");
            }
            System.out.println("Ending Wax On task");
        }

    }
}


