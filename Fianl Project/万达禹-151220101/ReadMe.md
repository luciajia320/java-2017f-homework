# 期末实验说明

## 实验结果
- 由于做实验前一天晚上对该游戏进行了一些充分的构思，所以造成了对于老师提供的游戏框架以及要求的一些疏漏，再加上时间限制已经来不及改动，这点还请老师谅解。但实验结果对于本人来说还算满意，因为第一次尝试游戏创作，自己原本的想法和设计也被或多或少的展示了出来。不管简陋与否，个人还是很享受这种能把自己的想法付诸实践并得以用程序展现出来的整个过程。虽然实验已经截止了，但在今后不断的学习过程中，自己还会渐渐地完善这个游戏。并希望有朝一日，这个游戏能够达到一个真正让人满意的结果。
 ***
 
## 游戏说明
### 游戏背景介绍
- 葫芦娃们在山洞中遇敌了！！但是山洞中充满了雾气看不清楚敌人具体的位置怎么办！？
- 身为玩家机智的你还请替他们出谋划策，成功帮他们击退蝎子精率领的攻击。
![image](https://github.com/Wandayu/java-2017f-homework/blob/master/Fianl%20Project/%E4%B8%87%E8%BE%BE%E7%A6%B9-151220101/%E5%AE%9E%E9%AA%8C%E6%88%AA%E5%9B%BE/%E6%B8%B8%E6%88%8F%E7%95%8C%E9%9D%A21.png)
***
### 游戏玩法介绍
- 本游戏是一款回合制的策略游戏。
- 本游戏的敌人成功的用云雾作为了掩护，将自己的位置隐藏了起来，因此你只能通过攻击的同时清除掉该位置的雾气。
- 敌人们有着随机的阵型，因此，如何利用敌人的阵型来使自己的攻击最大化也是必须要考虑的一点。
![image](https://github.com/Wandayu/java-2017f-homework/blob/master/Fianl%20Project/%E4%B8%87%E8%BE%BE%E7%A6%B9-151220101/%E5%AE%9E%E9%AA%8C%E6%88%AA%E5%9B%BE/%E6%94%BB%E5%87%BB%E6%88%AA%E5%9B%BE1.png)
- 当敌人全部死亡时，恭喜你获得了胜利！你会获得本次游戏详细的战斗记录。
![image](https://github.com/Wandayu/java-2017f-homework/blob/master/Fianl%20Project/%E4%B8%87%E8%BE%BE%E7%A6%B9-151220101/%E5%AE%9E%E9%AA%8C%E6%88%AA%E5%9B%BE/%E8%83%9C%E5%88%A9%E6%88%AA%E5%9B%BE.png)

### 游戏按键介绍
|选项|功能描述|
|:------|:----|
|Enter键|重新开局，重新分配阵型，对于难度过高的阵型推荐用哦|
|↑|读盘策略，欣赏上一局的精彩瞬间|
|→|读盘策略，按下可观看每一步|
|鼠标点击|选定攻击位置，葫芦娃！出击！|

### 游戏内容介绍
#### 游戏界面
- 游戏右侧为战场信息，上面记录了所有葫芦娃和敌人的位置，血量，攻击力。当然，未被发现的敌人的信息是不会出现的。当血量降为0时，该生物以及他的所有信息也都将从界面上消失。

- 游戏下方为战斗信息，上面记录了你采取的以及敌人采取的每一步策略，包括了攻击信息以及攻击效果。上面附有滚动条，因此你可以随时查看先前的每一步动作。
#### 角色信息
- 葫芦娃们性格各异，因此采取的攻击策略也各有不同，因此不同的葫芦娃有着不同的技能。

|葫芦娃|技能描述|
|:------|:----|
|大娃|大娃有着全动画中最大的力气，因此ATK也最高，分配给了大娃狂战士一般的AOE能力，能攻击指定位置以及右侧，下方，右下四个位置的能力。|
|二娃|二娃有着千里眼和顺风耳，有着弓箭手一般的侦察能力，因此能够洞察指定位置以及右侧，下方和右下四个位置的敌人。|
|三娃|三娃有着金刚不坏之身，因此也是所有葫芦娃中血量最高的角色。有着骑士一般的嘲讽能力，能够吸引所有敌人的火力。|
|四娃|四娃的能力是喷火，可以使用豪火球之术，具有可以攻击指定位置一整行的能力。|
|五娃|五娃的能力是喷火，可以使用大瀑布之术，具有可以攻击指定位置一整列的能力。|
|六娃|六娃的能力是隐身，是全动画中最灵活的能力，因此六娃具有盗贼一般的闪避能力，可以闪避来自下一次的攻击伤害。|
|七娃|七娃的能力是宝葫芦，因为游戏的平衡以及趣味，七娃的地位是作为一个奶妈，可以给全队每回合回复30点hp。|

![image](https://github.com/Wandayu/java-2017f-homework/blob/master/Fianl%20Project/%E4%B8%87%E8%BE%BE%E7%A6%B9-151220101/%E5%AE%9E%E9%AA%8C%E6%88%AA%E5%9B%BE/%E6%94%BB%E5%87%BB%E6%88%AA%E5%9B%BE2.png)

### 游戏代码介绍
#### 代码框架介绍
- 本次实验依旧延续开发了第三次葫芦娃排序的整体代码，并加入了游戏的控制部分以及整体的画图代码。

|------package Creature  Creature包内含了所有生物类<br>
&nbsp;&nbsp;&nbsp;&nbsp;|------class Creature  定义了生物的属性和方法，包括回复，攻击，技能等方法<br>
&nbsp;&nbsp;&nbsp;&nbsp;|------interface Creatures  定义了生物群落接口<br>
&nbsp;&nbsp;&nbsp;&nbsp;|------class HuluBoy  葫芦娃类，继承Creature<br>
&nbsp;&nbsp;&nbsp;&nbsp;|------class HuluBrothers  葫芦兄弟类，Creatures接口的实现，HuluBoy类的聚集<br>
&nbsp;&nbsp;&nbsp;&nbsp;|------class Enemies 敌人类，Creature接口的实现，提供了根据阵型需求自动更改敌人数量等功能<br>
&nbsp;&nbsp;&nbsp;&nbsp;|------class DaWa,ErWa,......,QiWa  每个葫芦娃自成一类，继承HuluBoy，具体实现skill功能以便动态链接<br>
    
|------package Formation  Formation包内含了所有阵型类<br>
&nbsp;&nbsp;&nbsp;&nbsp;|------interface Formation  定义了阵型接口<br>
&nbsp;&nbsp;&nbsp;&nbsp;|------FlyingGeese_Formation,...,Arrow_Formation  每个阵型自成一类，实现Formation接口规定的阵型方法<br>
    
|------package HuluTeam  HuluTeam包内含了所有游戏相关代码<br>
&nbsp;&nbsp;&nbsp;&nbsp;|------class TimeTurn  时间调度类，判断该回合是哪个葫芦娃的回合，并根据当前回合计算下个回合的生物<br>
&nbsp;&nbsp;&nbsp;&nbsp;|------class Position  位置类，记录了某一位置的可视信息，有无生物，以及具体生物<br>
&nbsp;&nbsp;&nbsp;&nbsp;|------class BattleField  战场类，继承JPanel，聚集Position,HuluBrothers,Enemies,Formation等，实现可视化功能，paint根据位置可视化信息，生物信息等加载生物入场，根据游戏是否开始实时更新文本框内容，根据生物攻击返回的信息实时repaint<br>
&nbsp;&nbsp;&nbsp;&nbsp;|------class Observer  管理观察类，继承JFrame，聚集BattleField,Timeturn，向窗口添加键盘和鼠标监听，规定了AI的攻击策略，负责读盘和复写<br>
&nbsp;&nbsp;&nbsp;&nbsp;|------class Game  游戏入口，Main函数所在类<br>

