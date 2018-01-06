# 大作业说明文档

## 一、类介绍
1. Field类：主要用于图形化显示以及响应消息,主要成员对象有:两个Creature数组，分别代表葫芦娃阵营和妖怪阵营；Formation对象,用于记录阵型的位置信息,供Creature使用；Position数组，由`NxN`个Position对象组成，记录`NxN`个位置的信息。主要成员函数有paint()画图函数，record()记录函数，readReord()读取记录函数，traceBack()回溯记录函数，traceForward()顺序读取记录函数。
2. Creature抽象类：主要成员数据有shutdownRequested(volatile static boolean类型，用于判定是否结束)、sleeptime(进程休眠时间)、waitflag(控制进程暂停和继续)、position(生物体所在位置)、alivestatus(判定生死)、speed(速率)、strength(力量)等。主要成员函数有run()进程运行函数、getRandomDelta()随机获取偏移量函数(使得生物体在以速率为半径的范围内随机移动)、move()移动函数、fight()战斗函数、currentAliveNumber()函数(计算当前阵营幸存数量，判定是否结束)、creatureWait()等待函数、creatureWakeup()唤醒函数等。Creature抽象类拥有以下子类：Huluwa、Laoyeye、Xiezijing、Shejing、Xiaoyaoguai，对应图片在各个子类的构造函数中初始化。其中，getRandomDelta函数，`delta = (rand.nextInt(speed)+1)*(rand.nextInt(3)-1)`使得生物体在速率范围内沿各个方向随机移动；fight函数用`power = r.nextInt(this.getStrength())`计算生物体的力量值，并比较两个生物体的力量值大小以决定生死，在伪随机数足够随机的情况下，概率由生物体的strength成员决定；在run函数中，使用`synchronized(field)`设置临界区，使得各个线程访问公用的field对象不会起冲突。
3. Formation类：记录阵型所包含的位置信息，判断阵型是否越过Field对象所规定的边界，判断两个阵型是否起冲突。拥有SnakeFormation和WingFormation两个子类，分别代表长蛇阵和鹤翼阵。
4. FileNOtExistException和OutOfBoundException类：都是Exception的子类，用于抛出相应异常。
5. Position类：管理二维空间上一个坐标点的相关信息，包括横纵坐标、位于该坐标上的生物、是否被占据、是否为边界，并提供获取这些信息和在该坐标设置生物体的对外接口。
6. Thing2D类：记录二维图片信息，包括显示的位置和Image对象。
7. Tile类：是Thing2D的子类，记录背景砖瓦图片的信息。
8. Ground类：设置窗口和Field对象，初始化UI。
9. Main类：应用程序入口。

## 二、功能介绍
1. 设置`NxN`的二维空间，该空间中的任意一个位置坐标上可且仅可站立一个生物体（葫芦娃、老爷爷、蛇精、蝎子精、小喽啰均属于生物体）。
2. 七个葫芦娃兄弟和老爷爷在空间左侧摆出“长蛇阵”，妖精（蛇精、蝎子精、小喽啰）在空间右侧摆出“鹤翼”阵，阵型位置可设定，有阵型位置是否异常判定。各个类对应的图片不同，葫芦娃阵营为红色，妖精阵营为绿色，图片上有各个对象的标志字符。
3. 所有生物体均实现为一个线程，在空间上随机移动，移动范围限定为以speed为半径的连通区域内，当某个生物体与敌方相遇(两者的X轴距离和Y轴距离小于1，即生物体的8连通区域)，两者根据各自strength计算出来的力量值决定生死，死者进程结束，留下实体但不占据空间(死亡Creature对象所记录的Position信息不会删除，但清除占据的空间)，生者继续寻找下一个敌人攻击。
4. 某一方生物体全部死亡时，设置`shutdownRequested = true`，结束所有进程。
5. 键盘响应：  
a)`空格`键：所有生物体线程执行`start()`，向敌方前进，当一方存活数量小于3时，主动追击对方。从按下空格开始时直到结束，整个过程都有记录，并保存到record目录中，一个生物体的每次移动都将被记录；  
b)`L`键：弹出对话框，让用户选择一个文件，读取文件内容，并按文件中记录的内容进行战斗回放；  
c)`↓`键：在按下L键之后，可以逐条向下查看记录内容并回放，有异常检查，并提示已经是最后的一条记录。中间仍可用L键重新定位；  
d)`↑`键：在按下L键之后，可以回溯上一条记录内容并回放，直到第一条记录，有异常检查。中间仍可用L键重新定位；  
e)`W`键：所有进程执行wait，暂停所有进程；  
f)`Q`键：唤醒所有进程；  
g)`R`键：在游戏结束后，重新开始游戏。  
6. 所有对象信息的访问均由规定接口提供，体现了OCP原则；Creature为抽象类，葫芦娃、老爷爷、蛇精、蝎子精、小喽啰等具体类都继承于Creature类，外部类使用的均是Creature引用，这体现了LSP和DIP原则，同时也是“多态”面向对象基本特性的体现。
7. 文件打开异常和生物体越过边界均会抛出异常，存储和读取记录使用输入和输出机制。
8. 为一些重要方法编写单元测试用例，并使用Maven进行构建管理。
9. 战斗场面设定如下：葫芦娃(速率=2，力量=4)、老爷爷(速率=1，力量=2)、蝎子精(速率=3,力量=5)、蛇精(速率=2,力量=4)、小喽啰(速率=2,力量=3)。

## 三、文件说明
1. 文件组织结构  
```
src  
    |----main  
        |----java  
                |----nju.java  
                        |----class  
    |----test  
            |----java  
                |----class  
    |----resources  
    |----records  

pom.xml
```  

2. 附件  
a) relation  
类关系导图目录  
b) result  
运行结果目录  

## 四、一些问题
1. 项目打包成.jar，虽然资源文件包含在target包里面，但由于jar包中文件URL有专用格式：jar:<url>!/{entry}，使得正常的文件路径不能被访问到，所以.jar文件运行时会因为找不到资源文件而抛出异常，要正常运行只能在java的IDE下编译运行，因时间所限该问题暂且搁置。

## 五、开发环境  
- Windows10
- IntelliJ IDEA Community Edition 2017.2
- JRE1.8.0_144
