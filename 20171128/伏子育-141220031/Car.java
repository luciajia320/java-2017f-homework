import java.util.concurrent.TimeUnit;

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
//    public synchronized void wax(String name) throws InterruptedException {
//        while (waxOn == true) {
//            wait();
//        }
//        System.out.println(name + ": Wax On! ");
//        TimeUnit.MILLISECONDS.sleep(200);
//        waxOn = true;
//        notifyAll();
//    }
//
//    public synchronized void buff() throws InterruptedException{
//
//        while(waxOn == false) {
//            wait();
//        }
//        System.out.println("Wax Off! ");
//        TimeUnit.MILLISECONDS.sleep(200);
//        waxOn = false;
//        notifyAll();
//    }

}
