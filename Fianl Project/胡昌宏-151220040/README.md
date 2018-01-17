### 简单介绍：
初步实现了战斗，即葫芦阵营和妖怪阵营碰面有一方会死亡，当某一方全部死亡时战斗结束。
简单实现了回放功能，将每个生物的每一步记录下来，回放时读取记录文件实现回放。

### 使用说明：
空格：游戏开始，然后等待结束。
L键：回放功能，会弹出选择窗口，选择“record.txt”，会读取它，然后回放。

### 具体实现：
模版使用了曹老师给的代码。

Creature是所有生物的抽象。下面有Huluwa，Grandpa，Snake，Scorpion，Goblin子类。
其中好人为Huluwa，Grandpa；敌人为其余三者。HuluCount和enemyCount分别为好人和坏人的人数，
每次创建一个好人，HuluCount加1，反之，enemyCount加1。当有生物死亡时，对应减1。某一方阵营
的人数等于0时，游戏结束。


#### 排兵布阵：
在String level里面修改，1-7为葫芦娃，p为爷爷，g为小喽啰，s为蛇精，c为蝎子精。创建相应的对象。
private String level = 
                    ".1........g....\n" +
                    "..2......g.....\n" +
                    "...3......g.c..\n" +
                    "p...4....g.....\n" +
                    "...5......g.s..\n" +
                    "..6......g.....\n" +
                    ".7........g....\n" +
                    "...............\n";

然后创建对应的线程，给它们run起来。

ThreadGrandpa = new Thread(grandpa);
ThreadGrandpa.start();
ThreadSnake = new Thread(snake);
ThreadSnake.start();
ThreadScorpion = new Thread(scorpion);
ThreadScorpion.start();


#### 人物介绍：

葫芦娃：俗话说的好，擒贼先擒王，葫芦娃如果走了n步后（默认n=20），还没有发现敌人就会改变策略。
先找蛇精的位置，如果蛇精死了或是有别的葫芦娃即将与蛇精交战（在同一行），就会找蝎子精，同样的如果
蝎子精死了或是有别的葫芦娃即将与其交战，就会找小喽啰。
![image](https://note.youdao.com/yws/public/resource/2beee9cde832978d0051437d013661df/xmlnote/2F0F6271FE184F57B7AAC5A0141EDA8A/29)

爷爷：爷爷肯定也是跟着葫芦娃走了，即行走策略是一致的。

蝎子精，蛇精，小喽啰：没办法，作为反派就要有这个觉悟，没有主角光环，所以他们非常的笨，只会一直往前走，左右来回。

交战算法其实就是生成随机数，在某一范围之内，获胜，反之，失败。


#### 回放：
回放的话，在每个生物每次move时，记录下该移动，从x1，y1到x2，y2，保存到txt文件里，然后回放时读取该文件即可。
Goblin2 550 90 490 90
Goblin7 610 390 550 390
Goblin3 610 150 550 150
Goblin1 600 30 540 30
Goblin5 610 270 550 270
Hu7 70 390 130 390
Goblin4 550 210 490 210
Hu3 190 150 250 150
Goblin6 550 330 490 330
Scorpion 730 150 670 150
Hu2 130 90 190 90
Hu6 130 330 190 330
Hu5 190 270 250 270
Hu4 250 210 310 210
Grandpa 10 210 70 210


### 总结
本次实验其实挺有意思的，毕竟老师就这么的有意思，这次实验不仅学会了很多java的知识，还使得我们要渐渐适应从被动
学习到主动学习，以及学习如何管理规划自己的时间来完成大作业。

### 效果截图
![image](https://note.youdao.com/yws/public/resource/2beee9cde832978d0051437d013661df/xmlnote/C8B211074C2B443E870137041CC6780B/31)

![image](https://note.youdao.com/yws/public/resource/2beee9cde832978d0051437d013661df/xmlnote/1822824975AD42F5A393D0DB1530DC6A/30)