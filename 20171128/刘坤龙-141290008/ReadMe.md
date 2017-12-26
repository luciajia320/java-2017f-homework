# 关于代码的说明

要求程序运行时存在两个`WaxOn`线程, 也就是说需要增加对这两个WaxOn线程的互斥管理.

考虑在car类里增加相应的, 类似于waxon的状态量occupied来指示某个car对象是否已经被某waxon线程占用

``` java
class Car {
  private boolean waxOn = false;
  private boolean occupied = false;
  public synchronized void waxed() {
    waxOn = true; // Ready to buff
    occupied = false;
    notifyAll();
  }
  public synchronized void buffed() {
    waxOn = false; // Ready for another coat of wax
    notifyAll();
  }
  public synchronized void waitForWaxing()
  throws InterruptedException {
    while(waxOn == false)
      wait();
  }
  public synchronized void waitForBuffing()
  throws InterruptedException {
    while(waxOn == true || occupied == true)
      wait();
    occupied = true;
  }
}
```

逻辑为:

当该车waxon或者被其他waxon线程占用时, 该waxon线程都需要继续等待

而当该车完成waxed后, 应清除占用