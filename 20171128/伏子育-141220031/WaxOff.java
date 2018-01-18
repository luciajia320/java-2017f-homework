import java.util.concurrent.TimeUnit;

class WaxOff implements Runnable {
    private Car car;
    //private String name;

    public WaxOff(Car c) {
        car = c;
        //this.name = name;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (car) {
                    car.waitForWaxing();
                    System.out.println("Wax Off! ");
                    TimeUnit.MILLISECONDS.sleep(200);
                    car.buffed();
//                car.buff();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending Wax Off task");
    }
}