# 胡语诚 141220042 javaFinalProject
## 结构介绍
葫芦娃项目以图形化应用示例为基础构建起来的，除了原有的Field、Groud、Player、Thing2D、Tile，额外增加了:  
Grandpa/Huluwa/Monster/Snake:分别指代爷爷、葫芦娃、蝎子精/小喽啰、蛇精，都是Player的子类  
Main:作为程序入口  
fileFilter/expPanel:对UI的扩展  
## 使用方法
运行后界面如图所示  
! [加载不出来，凉了] (https://github.com/exevvv/java-2017f-homework/blob/master/Fianl%20Project/%E8%83%A1%E8%AF%AD%E8%AF%9A-141220042/0.png)  
上排按钮可以为葫芦娃编排不同的阵型(鹤翼,雁行,衡轭,长蛇,鱼鳞,方圆,偃月,锋矢)(默认长蛇)  
下排按钮分别为:  
开始战斗(快捷键SPACE):GUI开始演示葫芦娃与妖精的大战，需要使用结束/重开按钮结束整个战斗  
结束战斗/重新开始(快捷键R):重置整个版面，此时可以重新使用开始战斗按钮进行下一次战斗演示，如果结束了一场战斗，会生成一个战斗过程的记录文件(HuluwaRecord~.txt)  
战斗回放(快捷键L):使用一个记录文件来进行战斗过程的回放  
说明(快捷键T):对于整个界面的说明  
! [加载不出来，凉了] (https://github.com/exevvv/java-2017f-homework/blob/master/Fianl%20Project/%E8%83%A1%E8%AF%AD%E8%AF%9A-141220042/1.png)  

