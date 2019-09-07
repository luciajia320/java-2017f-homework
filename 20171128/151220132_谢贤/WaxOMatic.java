import java.util.concurrent.*;

public class WaxOMatic {
    public static void main(String[] args) throws Exception {
    	/*���öγ��������ӿ���һ��WaxOn�̣߳�������ʱ��������WaxOn�̣߳��ֱ�����ΪWaxOn1 �� WanOn2����һ��WaxOff�̣߳�
    	 * ��waxOn==trueʱWaxOff��������waxOn==falseʱĳһ����������ĳ��ȷ���ģ�WaxOn�̹߳�����
    	 * ����ԭ�С�Wax On! \n������ַ���ǰ�������߳����ƣ����ֱ������WaxOn1: Wax On! \n���͡�WaxOn2: Wax On! \n������*/
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