import java.util.concurrent.TimeUnit;

class WaxOn implements Runnable {
    private Car car;
    int id;
    
    public WaxOn(Car c,int id) {
        car = c;
        this.id = id;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
            	car.wait4anotherWaxOn();
            	System.out.println("WaxOn" + id + ": Wax On! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitForBuffing();
            }
        } catch (InterruptedException e) {
        	System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending WaxOn"+ id + " task");
    }
}