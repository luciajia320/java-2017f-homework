## 原理
<<<<<<< HEAD
使用了一个waxchoice来确定是哪一个waxOn，对car的获取进行管理。

并且在有``wax``进到``car``进行操作的时候，block掉``waxOn``布尔变量，保证不在执行wax的时候有waxoff进来打乱整个现成的控制。

其中的waxID的打印和waxOn的打印分开是为了确保异步并体现在分开的句子执行之中没有另一个线程过来干扰。要不就会出现“waxOn1:wanOn2:waxOn!waxOn!”的情况。
=======
使用了一个``waxchoice``来确定是哪一个``waxOn``，对``car``的获取进行管理。

其中的``waxID``的打印和``waxOn``的打印分开是为了确保异步并体现在分开的句子执行之中没有另一个线程过来干扰。要不就会出现``“waxOn1:wanOn2:waxOn!waxOn!”``的情况。
>>>>>>> 6346d2b31535efd05626fcb139e6ee38ae4480e2

以上
