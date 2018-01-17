import java.util.concurrent.*;

public class WaxOMatic {
    public static void main(String[] args) throws Exception {
    	/*将该段程序中增加开启一个WaxOn线程，即运行时存在两个WaxOn线程（分别命名为WaxOn1 和 WanOn2）和一个WaxOff线程，
    	 * 当waxOn==true时WaxOff工作，当waxOn==false时某一个（但不是某个确定的）WaxOn线程工作，
    	 * 并在原有“Wax On! \n”输出字符串前面增加线程名称（即分别输出“WaxOn1: Wax On! \n”和”WaxOn2: Wax On! \n“）。*/
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));
        WaxOn WaxOn1=new WaxOn(car,"WaxOn1");
        WaxOn WaxOn2=new WaxOn(car,"WaxOn2");
        exec.execute(WaxOn1);
        exec.execute(WaxOn2);
        TimeUnit.SECONDS.sleep(5); // Run for a while...
        exec.shutdownNow(); // Interrupt all tasks
    }
}