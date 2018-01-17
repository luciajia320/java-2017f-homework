
import java.util.concurrent.*;


import static net.mindview.util.Print.*;



class Car {

    private boolean waxOn = false;

    public synchronized void waxed() {

        waxOn = true; // Ready to buff
        
        notifyAll();

    }



    public synchronized void buffed() {

        waxOn = false; // Ready for another coat of wax

        notifyAll();

    }



    public synchronized void waitForWaxing()

            throws InterruptedException {

        while (waxOn == false)

            wait();

    }



    public synchronized void waitForBuffing()

            throws InterruptedException {

        while (waxOn == true)

            wait();

    }

}

public class WaxOMatic {

    public static void main(String[] args) throws Exception {

        Car car = new Car();

        ExecutorService exec = Executors.newCachedThreadPool();
        
        exec.execute(() -> {
        	 try {

                 while (!Thread.interrupted()) {

                     car.waitForWaxing();

                     printnb("Wax Off!\n ");

                     TimeUnit.MILLISECONDS.sleep(200);

                     car.buffed();

                 }

             } catch (InterruptedException e) {

                 print("Exiting via interrupt");

             }

             print("Ending Wax Off task");
        });
        
        exec.execute(() -> {
        	try {

                int num = 1;
                while (!Thread.interrupted()) {
                	
                	synchronized(car) {
                	
                	 car.waitForBuffing();
                
                    printnb("WaxOn" + num + ":" + "Wax On!\n ");

                    TimeUnit.MILLISECONDS.sleep(200);

                    car.waxed();

                }
                
                }

            } catch (InterruptedException e) {

                print("Exiting via interrupt");

            }

            print("Ending Wax On task");

        });
        
        exec.execute(() -> {
        	try {

                int num = 2;
                while (!Thread.interrupted()) {
                	
                	synchronized(car) {
                	
                	 car.waitForBuffing();
                
                    printnb("WaxOn" + num + ":" + "Wax On!\n ");

                    TimeUnit.MILLISECONDS.sleep(200);

                    car.waxed();

                }
                
                }

            } catch (InterruptedException e) {

                print("Exiting via interrupt");

            }

            print("Ending Wax On task");

        });

        TimeUnit.SECONDS.sleep(5); // Run for a while...

        exec.shutdownNow(); // Interrupt all tasks
        
    }

}



