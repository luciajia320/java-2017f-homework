# HULUWA-FATE命运之战
## 介绍
- 葫芦娃（裁定者和从者）大战妖怪（复仇者和alter_rgo）是一款Java语言编写的小游戏（动画）。
- 正邪双方在地图上随机布阵，随后可展开激烈的战斗争夺圣杯
- 可战斗，可重置战场，可记录战争
## 世界观
- 这个世界上存在着一种强大的魔术力量，魔术师们凭借这种力量可以做到许多超自然的事情，这种力量的起源就是被称为万能的许愿机的圣杯。圣杯是一切魔术的根源，可以实现任何愿望，是每个魔术师梦寐以求的宝贝，然而它的每一次降临都是一场灾难。
## 故事背景
- 这一次，圣杯降临在了冬木市，上一次圣杯战争中被魔术协会会长宝石翁封印的邪恶魔法师伏地魔冲破了封印，化身为更加强大的复仇者，联合邪恶的alter_ego想要夺取圣杯，利用这次战争一举摧毁人类。魔术协会派出了新上任的裁定者，协同召唤出的七个职介的从者（剑士、弓兵、枪兵、魔法师、骑兵、暗杀者、狂战士），为了捍卫正义而战。于是，在冬木市展开了这场决定人类命运的战争。
***
## 游戏功能
|选项|功能描述|
|:------|:----|
|s|战斗开始，双方互相追击厮杀|
|r|重置战场，以新的随机阵型战斗|
|↓|弹出结果对话框|
|→|将本次对战记录在文件中|
|↑|将文件中的故事读到控制台|
***
## 游戏内容
- ruler和七个servant以随机的阵型站在战场左侧
- avenger和alter_ego以随机阵型站在战场右侧
- 当按下s键时，正邪双方开始互相接近，当不同阵营的两人相遇后，便开始互相厮杀，失败的一方被打回英灵座
- 当一方全部阵亡时，另一方便获胜，获胜的一方可以抵达地图左下角的圣杯处，夺得圣杯
## 游戏规则
- 每个个体在遇到敌人是就会发起战斗，主动出战胜率为60%，被动迎战胜率为40%；做没有遇到敌人则继续向有敌人的方向前进
## 游戏截图
#### 阵型1
![image](https://github.com/Dead96Beat/java-2017f-homework/blob/master/Fianl%20Project/final_project/葫芦娃报告截图/对阵1.png "应用截图")
#### 阵型2
![image](https://github.com/Dead96Beat/java-2017f-homework/blob/master/Fianl%20Project/final_project/葫芦娃报告截图/对阵2.png "应用截图")
#### 阵型3
![image](https://github.com/Dead96Beat/java-2017f-homework/blob/master/Fianl%20Project/final_project/葫芦娃报告截图/对阵3.png "应用截图")
#### 阵型4
![image](https://github.com/Dead96Beat/java-2017f-homework/blob/master/Fianl%20Project/final_project/葫芦娃报告截图/对阵4.png "应用截图")
#### 战斗
![image](https://github.com/Dead96Beat/java-2017f-homework/blob/master/Fianl%20Project/final_project/葫芦娃报告截图/战斗.png "应用截图")
#### 结束
![image](https://github.com/Dead96Beat/java-2017f-homework/blob/master/Fianl%20Project/final_project/葫芦娃报告截图/结束.png "应用截图")
#### 战斗复盘
![image](https://github.com/Dead96Beat/java-2017f-homework/blob/master/Fianl%20Project/final_project/葫芦娃报告截图/战斗复盘.png "应用截图")
#### 战斗故事
![image](https://github.com/Dead96Beat/java-2017f-homework/blob/master/Fianl%20Project/final_project/葫芦娃报告截图/战斗故事.png "应用截图")
***
## 代码框架
### 框架说明:
- 本次大实验基本沿用老师所给的示例框架，在此基础上进行添加修改，以JFrame和JPanel为框架搭建图形界面；以多线程的
方式控制角色运动；使用synchronized机制来实现多线程同步。
### 个体：
* Thing2D，所有角色的基类，包含坐标、图像、名字、死亡标记属性；有设置图像，设施死亡标记以及一系列读取功能
* Player，extends Thing2D implements Runnable，新增了一个field属性，可以感知自己所在战场，观察同行有没有敌人
以及地图上有无剩余地方；有控制移动的策略move；有run方法
* Ruler， extends Player implements Boss，正义一方的领袖
* servant， extends Player，正义一方的从者，有不同的职介，属于正义阵营
* avenger， extends Player implements Boss，邪恶一方的领袖
* alter_ego， extends Player ，邪恶一方的小兵
* Space，空白（特殊的生物，如空气一般虚无缥缈）
### 策略：
* strategy，所有策略的基类（注：所有阵型均为两阵营对称）
* ArrowStrategy
* GapStrategy
* GooseStrategy
* RecStrategy
* SnakeStrategy
### 辅助类：
* score，用于比赛计分的类，其中数据和方法均为静态
* story，用于记录战斗并与文件交互的类，包括静态的读写文件的方法
* newWindow，为了弹出比赛结果新建的一个窗口
* Team， 阵营类，包含阵营标记isKind、角色队列players、队伍策略ourStrategy属性;具有随机生成策略，按阵营添加角色的方法
### 战场元素：
* Point， 包含横坐标x、纵坐标y、占有本位制的角色的引用holder；具有报告坐标，报告持有者的方法。
* field， extends JPanel， 具有OFFSET 、SPACE、宽w、高h、正义队伍goodTeam、邪恶队伍badTeam、胜利奖杯victory、
点集points等属性；具有绘制背景，设置按键驱动，创建世界，返回点集信息的方法
### 框架：
* Main， extends JFrame，初始化窗口，并控制程序开始执行
### Test/测试
主要对field.Field中的方法进行测试，包括：
* Add方法是否能正确在指定位置放置生物。
* Delete方法是否能正确移除指定位置的生物。
* IsRowHaveEnemy方法是否能正确判断该行是否存在敌人。
* IsGameOver方法是否能正确判断游戏是否结束。
***
## 结束语
- Java游戏的开发过程还是很有趣的，虽然遇到了很多的问题，但是查找资料并尝试解决问题之后的成就感很让人满足。
- 上这门课学到了很多知识，比如以前没有尝试过的并发编程，虽然考试的时候不太理想，但是能写出这个游戏（故事）很让人满足。
- 改掉了拖延症，写作业效率变高了。
- **写应用同时也要会Photoshop啊...抠图拼图什么的**
- 本项目中所有资源均来自互联网，在此向资源提供者致谢，如有侵权请联系作者<liu@triumphal.cn>删除。
- 感谢曹老师余老师的授课，merci.
- 如对此项目有意见或建议欢迎交流:<m13270895277@163.com>
***
