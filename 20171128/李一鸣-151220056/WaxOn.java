import java.util.concurrent.*;
public class WaxOn implements Runnable{
    private Car car;
    private int cno;
    public WaxOn(Car c,int n) {
        car = c;
        cno = n;
    }
    public void run() {
        try {
          while (!Thread.interrupted()) {
              car.ifExitCar();
              System.out.println("WaxOn"+cno+":Wax On! ");
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
