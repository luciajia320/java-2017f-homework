import java.util.concurrent.*;

class Car{
    private boolean waxon=false;
   private boolean OneWaxing=false;
    public synchronized void waxed(int id){
        if(OneWaxing==false) {
            OneWaxing=true;
            waxon = true;
            System.out.print("Wax" + id + " On!\n");
            notifyAll();
        }
    }
    public synchronized void buffed(){
        waxon=false;
        OneWaxing=false;
        notifyAll();
    }
    public synchronized void waitForWaxing()
        throws InterruptedException{
            while (waxon==false)
                wait();
        }
    public synchronized void waitForBuffing()
        throws InterruptedException{
            while (waxon==true)
                wait();
        }
}

class WaxOn implements Runnable{
    private Car car;
    private int id;
    public WaxOn(Car c,int id){car=c;this.id=id;}
    public void run(){
        try{
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed(id);
                car.waitForBuffing();
            }
        }catch (InterruptedException e){
            System.out.print("Exiting via interrupt");
        }
        System.out.print("Ending Wax" +id+" On task");
    }
}

class WaxOff implements Runnable{
    private Car car;
    public WaxOff(Car c){car=c;}
    public void run(){
        try{
            while (!Thread.interrupted()){
                car.waitForWaxing();
                System.out.print("Wax Off!\n");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            }
        }catch (InterruptedException e){
            System.out.print("Exiting via interrupt");
        }
        System.out.print("Ending Wax On task");
    }
}

public class WaxOMatic {
    public static void main(String[] args) throws Exception{
        Car car=new Car();
        ExecutorService exec=Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));
        exec.execute(new WaxOn(car,1));
        exec.execute(new WaxOn(car,2));
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
}
