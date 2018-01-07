# 葫芦娃大战妖精-151220023

## 首先是整个的输出

![输出结果](1.jpeg)

## 然后是整个的结构图

![文件结构](2.jpeg)

## 综述

使用了``接口``、``继承``、``聚合``的面向对象概念。如上图所示。<br>

#### 接口

因为``sort``、``creature``、``compare``均为生物类需要进行继承的，因此抽象为借口，然后每个不同的生物进行实现，减少代码，增加逻辑性。使得整个的区域可以放下不同的实现，但是都是同一个父类。

#### 继承

考虑到以后可能需要的东西，我创造了``monster``和``Xiaojingang``两个大的类，实现了``creature``然后让各自的葫芦娃、蝎子精、蛇精、小喽啰等进行继承，简化了代码。

#### 聚合

因为``Square``类是整个的区域，需要``position``进行聚合，还有``creature``，然后每个``position``上有``creature``。这样能够使得层次更加清晰。而且Square上能够放入多种生物。<br>
因为需要进行阵的变换，因此``Formation``只有``monster``的类型才能够获取。<br>
其实最重要的是看上面的结构图啦～

#### 容器

最新的更新使用了容器类，精简了葫芦娃的一部分内容。



### 问题以及解决办法

1. **ConcurrentModificationException 异常**
   * 在使用多线程的时候出现的异常，因为我要使用`field`中的`world`进行遍历然后选择出可以攻击的敌人，当多线程进行访问的时候就会出现冲突，因为`ArrayList`本身是非线程安全的容器，当我需要同时共用同一个`world`变量，线程异步之后会出现这样的情况：`Dawa`和`Erwa`同时遍历，`Dawa`将`Soldier`杀死并去除它，但是`Erwa`还在遍历，并且刚好需要用到该对象的一个值比如说`p.getCamp()`这样就出现了这样的异常。
   * 解决：因为必须在异步中同时处理，因此不能使用线程同步的加锁的办法，只能让每个葫芦娃在判断前进行一份拷贝，这样相当于`ThreadLocal`的处理办法。然后在获取拷贝的时候进行`synchronized`进行访问控制，然后针对`world`使用`CopyOnWriteArrayList`进行创建，可以保证线程安全。
2. ​













### 参考文献

* https://www.2cto.com/kf/201403/286536.html
* http://blog.csdn.net/androidboy365/article/details/50540202/
* https://stackoverflow.com/questions/34598213/why-does-executorcompletionservice-does-not-use-threads-from-executorservice-poo
* ​