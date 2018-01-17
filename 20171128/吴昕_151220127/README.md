在原代码的基础上
> * 增加ReentrantLock锁，控制两个waxon进程。当一个waxon作业的时候锁住资源区防止另外一个waxon进程冲突。
> * buffed()函数中notifyAll()改成notify()，即只需唤醒任一个后台进程。

[^java]: /*Output:
Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Exiting via interrupt
Ending Wax On task
Exiting via interrupt
Ending Wax Off task
*///:~