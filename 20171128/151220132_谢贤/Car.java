class Car {
    private boolean waxOn = false;

    public synchronized void waxed(String waxName) {
    	System.out.println(waxName+" Wax On! ");
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

    public synchronized void waitForBuffingAndwaxed(String waxName)
            throws InterruptedException {
        while (waxOn == true)
            wait();
        waxed(waxName);
    }
}