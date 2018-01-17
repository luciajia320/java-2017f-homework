
#### 解决方案
增加了一个成员变量 isWaxing 表示有人正在打蜡
因为有两只机械手需要上蜡，并且在抛光之前只能有一只工作，在WaxOn1和WaxOn2需要打蜡之前，都要询问，只有无人正在上蜡并且waxOn = false（即还没有上过蜡）时才可以进行上蜡，否则就阻塞自己。

```
    private boolean isWaxing = false;

    public synchronized void waxed() {

        waxOn = true;
        isWaxing = false;
        notifyAll();
    }

    public synchronized void wantToWax()
            throws InterruptedException
    {
        /*有人正在打蜡或者已经上蜡则等待*/
        while (isWaxing || waxOn)
            wait();
        isWaxing = true;
    }
```
    
这样，在他们需要打蜡之前要确认是否可以获得资源：</br>


```
class WaxOn1 implements Runnable {
...
   public  void run() {
        synchronized(car)
        {
            try {
                while (!Thread.interrupted()) {
                    car.wantToWax();
                    System.out.println("WaxOn1: Wax On! \n");
                    ...
                }
            } 
        }
    }
}

```
