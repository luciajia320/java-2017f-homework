import java.util.concurrent.TimeUnit;

public class WaxOff implements Runnable {
    private Car car;
    private int number;

    public WaxOff(Car c, int number) {

        this.number = number;
        car = c;
    }

    public void run() {
        synchronized (car) {
            try {
                while (!Thread.interrupted()) {
                    car.waitForWaxing();
                    TimeUnit.MILLISECONDS.sleep(200);
                    System.out.println("WaxOff" + this.number + ":Wax Off! \n");
                    car.buffed();
                }
            } catch (InterruptedException e) {
                System.out.println("Exiting via interrupt");
            }
            System.out.println("Ending Wax Off task");
        }

    }
}
