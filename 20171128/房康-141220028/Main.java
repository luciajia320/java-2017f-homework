import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {
        Car car = new Car();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new waxThread(1, car));
        service.execute(new waxThread(2, car));
        service.execute(new buffThread(car));
        service.shutdown();
    }
}
