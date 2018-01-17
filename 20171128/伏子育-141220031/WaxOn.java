import java.util.concurrent.TimeUnit;

class WaxOn implements Runnable {
    private Car car;
    private String name;

    public WaxOn(Car car, String name) {
        this.car = car;
        this.name = name;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (car) {
                    System.out.println(name + ": Wax On! ");
                    TimeUnit.MILLISECONDS.sleep(200);
                    car.waxed();
                    car.waitForBuffing();
//                car.wax(name);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending Wax On task");
    }

}