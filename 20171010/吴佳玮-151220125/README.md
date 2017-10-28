# README.md

------
## 程序结构

基于老师给的例子，做进一步更改扩充（之前自己写的太烂了）。<br>
![](https://github.com/thunderning/java-2017f-homework/blob/master/20171010/吴佳玮-151220125/类图.png)

## 具体解释

1. 所有生物被分为正义和邪恶两派，分别**继承**自`JusticeCreature`和`EvilCreature`，以备日后的改进
2. 阵法基类`Embattle`实现了在战场中列阵的功能，其子类`Rand``Echelon``Queue``Crane`分别表示`随机站``雁行``长蛇``鹤翼`，用以对战场上的生物进行操作
3. 'BattleField'战场类记录了战场的全部位置，实现了'show()''clear()'等函数用以显示或清除战场
4. `StoryLine`故事线类是主类，囊括了一个**战场**，四种**战阵**，四类**生物**，以及发生在这些元素身上的故事情节

## 运行效果

1.七个葫芦娃正在无忧无虑地玩耍
