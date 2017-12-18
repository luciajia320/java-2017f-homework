## 原理
使用了一个waxchoice来确定是哪一个waxOn，对car的获取进行管理。；
其中的waxID的打印和waxOn的打印分开是为了确保异步并体现在分开的句子执行之中没有另一个线程过来干扰。要不就会出现“waxOn1:wanOn2:waxOn!waxOn!”的情况。;
以上
