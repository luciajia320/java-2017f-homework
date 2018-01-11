#葫芦大战蛇蝎

---
######`It's a document about how to operate the game/drama.`



![QQ20180111-135507](/Users/ViviansMac/Desktop/杜静娴-151220021/pics/QQ20180111-135507.png)

![4DA9CFD3-4B55-4720-B087-9C0A377C64D6](/Users/ViviansMac/Desktop/杜静娴-151220021/pics/4DA9CFD3-4B55-4720-B087-9C0A377C64D6.png)

----

#如何开始

- 开始：`Space`
- 保存：自动
- 结束：`Esc`
- 重新开始：`R`
- 载入历史记录：`L`

---

#类层次描述

> # UML Diagram

![C2724EB2-CE81-460B-8F3C-2F2D58CF9F65](pics/C2724EB2-CE81-460B-8F3C-2F2D58CF9F65.png)

> #Ground
- 继承自JFrame
- 程序入口
- 初始化UI
- 与生俱来

> #Field
- 继承自JPanel
- 一个适合打架看戏的场馆
- 部署葫芦娃、爷爷、蛇精等妖怪
- 管理战斗的状态机
- 同时也是控制器

> #WarMonitor
- 裁判员角色，需要自由，当然是一个线程
- 裁定场上运动合法性
- 犯规者打回重来直到学会做人
- 观察即将发生的战争并通知当事人
- 尽量留给每一个人足够的时间来选择下一步动作
- 判定战斗的结束通知场馆暂停营业

> #Logger
- 笔录角色
- 将场上人物的状态变化记录成文件
- 复盘的证人

> #StoryTeller(interface)
- 会讲故事的人
- 需要一点自由(独立线程)
- 忠诚于写好的台词

> #Thing2D
- 可能这就是脸面和地位

> #Player
- 实现Runnble接口的能自由活动的人
- 运动员们上场了，这其实是个代表
- 在场馆内出生，外面的人知道他们的联系方式
- `Huluwa`，`Yeye`，`Xiezi`，`Shejing`，`Louluo`才是具有个性的人

> #Mutex
- 保管钥匙的人
- 指挥大家打蜡、擦蜡的，总之去一些奇怪的地方得问他借钥匙
- 条件就是必须上了十六层蜡才能让除蜡的进来擦一层
- 但十六层满了以后也不会再允许打蜡的进去
- 运动员都在打蜡
- 裁判员在除蜡

> #Enums
> 队形 `Format`  
> 葫芦 `Hulu`

> #PositionGenerator
- 可能大家都看不懂列队图吧需要他来指点
- 可以问他下一个站在哪


# 抽象&封装

- `Player`基类：描述生物的共同的方法，实现Runnable接口

  获取状态：isAlive，isStopped等

  多线程：run控制各自在每一个刷新间隙的动作

#继承

- `Player`基类：`Huluwa`，`Yeye`，`Shejing`，`Xiezi`，`Louluo`等子类

#多态

- 每一个`Player`有自己的行动方式，`WarMonitor`只需要知道自己指挥的是一群`Player`，`Player`就能正确执行各个生物的不同行为。

# 设计原则

- SRP单一指责

  `StoryTeller`只需要负责将log记录的故事叙述出来

  `Player`只需要做下一步行进的提议，以及攻击对手

  `FileAgent`只需要提供文件读写功能

  `Logger`只需要组织log并使用FileAgent存取

- OCP开放封闭

  新增加`Player`只需要继承`Player`基类，重写接口，不需要修改`Player`的代码

- LSP里氏替换

  每一种实际的`Player`在类外部的使用中都用`Player`来替代

- ISP接口隔离原则

  `Player`不需要知道如何排兵布阵，只需要使用者告诉`PositionGenerator`用什么队形，它负责观察队列“图纸”，负责排列

- DIP依赖倒置原则

  具体的生物都依赖于`Player`基类，不需要`Player`去询问运行时的具体类

- 观察者模式

  ​	裁判角色`WarMonitor`控制`Field`的每一个刷新间隙场上的变化，`Player`具有一定的能动性，即run各自的run，但是`Mutex`变量可以控制每一轮行为按照一定的次序发生，即每一个`Player`行动，然后`WarMonitor`裁决，`WarMonitor`判断每一个人的动作是否合法，不合法的打回重做，合法的予以确认，然后判断每两个对手之间的状态变化，让走到战争开始边缘的`Player`开始战斗，做完这些再向Field点头示意刷新。

- 适配器模式

  `Field`配置了`TAdapter`键盘响应器

- 装饰器

  文件读写使用装饰器



