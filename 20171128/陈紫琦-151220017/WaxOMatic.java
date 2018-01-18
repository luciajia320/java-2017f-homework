import java.util.concurrent.*;

class Car
{
    private boolean waxOn = false;      //是否已涂蜡

    public void waxed()   // 已涂蜡，准备抛光
    {
        waxOn = true;
        notifyAll();
    }

    public void buffed()   //已抛光，准备涂蜡
    {
        waxOn = false;
        notifyAll();
    }

    public void waitForWaxing()    //等待涂蜡过程
            throws InterruptedException
    {
        while (waxOn == false)
            wait();//挂起
    }

    public void waitForBuffing()   //等待抛光过程
            throws InterruptedException
    {
        while (waxOn == true)
            wait();//挂起
    }
}

class WaxOn implements Runnable //涂蜡
{
    private Car car;
    private int id;
    public WaxOn(Car c, int id)
    {
        car = c;
        this.id = id;
    }

    public void run()
    {
        try
        {
            while (!Thread.interrupted())
            {
                synchronized (car)
                {
                    System.out.println("Waxon" + id + ": Wax On!");       //开始涂蜡
                    TimeUnit.MILLISECONDS.sleep(200);       //涂蜡200ms
                    car.waxed();        //改变car的状态为已涂蜡
                    car.waitForBuffing();   //等待抛光过程结束
                }
            }
        } catch (InterruptedException e)
        {
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending Wax On task");
    }
}

class WaxOff implements Runnable
{
    private Car car;

    public WaxOff(Car c)
    {
        car = c;
    }

    public void run()
    {
        try
        {
            while (!Thread.interrupted())
            {
                synchronized (car)
                {
                    car.waitForWaxing();        //等待涂好蜡
                    System.out.println("Wax Off!");      //开始抛光
                    TimeUnit.MILLISECONDS.sleep(200);       //抛光200ms
                    car.buffed();   //改变car的状态为未涂蜡
                }
            }
        } catch (InterruptedException e)
        {
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending Wax Off task");
    }
}

public class WaxOMatic
{
    public static void main(String[] args) throws Exception
    {
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));
        exec.execute(new WaxOn(car, 1));
        exec.execute(new WaxOn(car, 2));
        TimeUnit.SECONDS.sleep(5); // Run for a while...
        exec.shutdownNow(); // Interrupt all tasks
    }
}