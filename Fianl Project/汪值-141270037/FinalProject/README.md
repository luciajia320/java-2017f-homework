## Java-2017 Final Project


### 战斗力系统

- 爷爷: 隐藏属性, 3.0

- 大娃: 半肉战士, 力大无穷, 综合能力较强, 9.0

- 二娃: 指挥, 辅助, 非主战力, 千里眼顺风耳, 5.0

- 三娃: 肉坦, 双抗全场第一, 7.0

- 四娃: ADC, 输出爆炸, 脆皮, 10.0

- 五娃: AP法师, 技能特效无敌, 7.0

- 六娃: 刺客, 隐身, 偷袭能力很强, 9.0

- 七娃: 外挂携带者, 团控无敌, 12.0


- 蛇精: AP法师, 20.0

- 蝎子精: AD战士, 15.0

- 小喽喽: 小兵, 2.0

- 战斗力判定是根据双方的战斗力值生成一个概率百分比, 战斗力越大, 获胜概率越强


### ID

葫芦娃  0-6
爷爷    7

蛇精    8
蝎子精  9
小喽喽  10-15


### 方向

0 - 向上
1 - 向右
2 - 向下
3 - 向左  

----

- version 0.1 修改作业3的程序, 并且将 GodIsGod.java 中所有生物站队 改到 Field.java中的initWorld() 中.

- version 0.2 修改所有生物的构造函数, 加入参数Field field, 让所有生物共用同一个field.

- version 0.3 让所有的生物继承 Player 类

- version 0.4 在Field中添加 ArrayList players, 用于存放所有的生物. 将生成的Position二维数组中的生物加入到 player 动态数组

- version 0.5 在Field中添加map二维数组判断指定位置是否有Player, 并且用 `private Lock lock = new ReentrantLock();` 进行互斥操作. 
在Field中添加战斗力属性 `double fightPower`

- version 0.6 完成战斗功能,和战斗场景的构建 (^_^)

- version 0.7 修复一些bug

- version 0.8 添加JUnit单元测试

- version 0.9 添加战斗回放功能

- version 1.0 正式版本上线






















