# 葫芦娃大战妖精大作业说明

---

葫芦娃大战妖精大作业说明将包含以下方面：
> * 基本思想
> * 类型说明及代码简介
> * 使用说明及运行情况
> * 测试代码



## 基本思想

在样例代码的基础上，我将Player作为定义葫芦娃、老爷爷、小喽啰、蛇精和蝎子精这些对象的的类。然后，在Field中初始化这些对象，将其按相应的阵型设置好坐标，创建一个ArrayList来存储这些对象。当按下空格时，将这些对象装入线程中并且开始run，葫芦娃队和蛇精队就会相向而行。每当战场上的一个对象进行移动时，要判断即将前往的位置是否有存活的单位，没有存活单位占据位置时才能进行移动，然后会判断其前进方向是否有敌人，若有敌人，则该对象和敌人都会进入战斗状态进行战斗。经过一段时间的战斗后，战斗的双方会有一个阵亡，一个胜利。阵亡的一方留下尸体，胜利的一方去寻找剩下的敌人进行战斗。当场上只剩下一方阵营时，战斗结束。

## 类型说明及代码简介


1. **Player**类型描述的时战场上战斗的生物，其主要的域和方法如下:

| 类型   |  域  |  含义  |
| :----: | :----:  | :----:  |
| int | strength  | 战斗力 |
| String| name | 名字 |
| boolean | alive | 是否存活 |
| boolean | search | 是否开始寻找敌人 |
| boolean | fighting | 是否在战斗状态 |
| int | team | 所属阵营 |
| DIR | dir | 前进方向 |


| 方法  |  含义  |
| :----: | :----:  |
| void attack()  |  进入战斗状态  |
| void die()  |  死亡  |
| void normal()  |  恢复正常（退出战斗状态）  |
| void sleep(int time) | 休眠一段时间 |

2. **Field**类型描述战场，其主要的域和方法如下:

| 类型   |  域  |  含义  |
| :----: | :----:  | :----:  |
| ArrayList<Player> | players  | 战场上的生物 |
| Record | record | 用于记录战场的对象 |
| boolean | completed | 整场战斗是否接受 |
| Replayer | replayer | 用于回放战场记录的对象 |
| boolean | fighting | 战斗是否正在进行 |
| FileChooser | fileChooser | 文件选择框对象 |

| 方法  |  含义  |
| :----: | :----:  |
| void initWorld()  |  战场初始化 |
| void perform(String str)  |  执行一条战场记录  |
| boolean available(int x,int y) |  判断（x，y）位置是否有生物  |
| boolean nearby(int x1,int y1,int x2,int y2,DIR dir) | 判断两个生物的坐标（x1，y1）（x2，y2）的是否相邻 |
|  boolean writeRecord(String str) | 写一条战场记录 |
|  boolean encounter(Player player) |  判断player是否与敌方生物相遇  |
| boolean search(Player player) |  查找player敌方阵营敌人的位置  |

3. **Record**用于文件IO，其主要方法如下:

| 方法  |  含义  |
| :----: | :----:  |
| void open(String filename)  |  打开文件，创建输入输出流 |
| void close()  |  关闭文件，关闭输入输出流  |
| boolean writeRecord() |  将战场记录写入文件  |
| String readRecord() | 从文件读战场记录 |

4. **Replayer**用于战场记录回放，它implements了Runnable借口，其run方法主要流程是循环一条条地读文件中的战斗记录，然后调用Field.perform(String str)来执行战场记录实现回放。


**代码的运行逻辑**：从main函数中创建Ground对象，然后在Ground的构造函数中创建Field对象，接着在Field函数中创建葫芦娃，老爷爷，小喽啰，蛇精和蝎子精对象，将葫芦娃队按一字长蛇阵排列，蛇精队按鹤翼阵排列，最后显示在屏幕上。然后可以按下空格键，将战场上的生物都装入线程中运行，他们的run方法中会循环调用move方法进行移动，每次准备移动到下一个位置时，调用Field.available方法来判断下一个位置是否被存活的单位占用，被占用则不移动，否则移动到下一个位置。不管是否移动了，都调用Field.encounter方法来判断前进方向上的下一个位置是否有敌人，有敌人则同事调用自己和敌人的attack方法进入战斗，然后会根据双方的strength来生成战斗胜利的概率，依照概率按用随机方法来决定双方生死，胜者调用normal方法恢复正常，败者调用die方法死亡。然后将胜者的search变量置为true，使其调用Field.search方法寻找敌人的位置，向敌人进行移动。战斗持续进行，当战场上只剩下一方阵营时，将Field.completed置为true，战斗结束。


## 使用说明及运行情况

运行程序后进入初始界面，葫芦娃按一字长蛇阵排列，蛇精队按照鹤翼阵排列
![init](http://i1.bvimg.com/626694/053eb1744bc395a2.png)
然后在按下空格键，葫芦娃队和蛇精队的成员开始向敌方前进，相遇之后会进入战斗状态
![fight](http://i1.bvimg.com/626694/33e87e4fc412998c.png)
战斗结束后，战斗的双方根据概率随机选择一方死亡，一方胜利，死亡的一方变成墓碑，胜利的一方会回复平常状态，胜利者会搜寻战场上剩下的敌人，向他们前进。最后战场上只剩下某一方的阵营时，战斗结束，所有存活的单位停止行动，左上角出现Completed字样
![completed](http://i1.bvimg.com/626694/857f68d7bdcbee3f.png)
战斗结束后，可以按下“S”键来保存战斗记录，会弹出一个提示框提示输入保存的文件名
![save](http://i1.bvimg.com/626694/a7e1f340c57a8f0d.png)
然后可以按“R”键恢复初始状态,在战斗开始或者战斗结束后，都可以按下“L”键来回放战斗记录，按下后会弹出文件选择框来选择文件，选择完文件后回放开始。
![load](http://i1.bvimg.com/626694/b5b56f0bb54c64b6.png)

## 测试代码

我对Field的Available函数进行了单元测试，因为这个函数涉及到了线程安全问题，即一个位置是否能同时被两个存活的生物占用。所以，我创建了两个Player线程让他们同时移动到同一个位置，最后判断他们的坐标是否相同。