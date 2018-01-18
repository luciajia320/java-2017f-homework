import java.util.concurrent.TimeUnit;

class WaxOn implements Runnable {
    private Car car;
    private String name;

    public WaxOn(Car c,String name) {
        car = c;
        this.name=name;
    }

    public  void run() {
    	
	        try {
	            while (!Thread.interrupted()) {
	                TimeUnit.MILLISECONDS.sleep(200);
	                car.waitForBuffingAndwaxed(name);
	                //car.waxed(name);	  
	            }
	        } catch (InterruptedException e) {
	        	System.out.println("Exiting via interrupt");
	        }
    	
        System.out.println("Ending "+name+" On task");
    }
}
