# 葫芦娃作业

### How to run
```$xslt
    mvn package
    java -jar ./target/Huluwa-1.0.0.jar
```
### 操作说明
* 按空格键时所有生物体向敌方前进
* 按L键可以选择一个文件，读取文件内容，并按文件中记录的内容进行战斗回放；
* 按R键可以重新初始化地图；

### 代码结构
```
Package -----constant       //定义一些常量
         |      |--- AppConfig.java      //应用配置
         |      |--- GameParams.java     //游戏参数
         |      |--- Values.java     //全局使用的常量
         |      
         |---model          //模型
         |      |--- actor      //角色
         |      |       |--- Actor.java //所有角色的父类
         |      |       |--- ActorFactory.java  //角色工厂类
         |      |       |--- Grandpa.java   //爷爷
         |      |       |---  ...       //葫芦娃，蝎子，小怪等角色
         |      |       |--- Snake.java //蛇精
         |      |       |--- Fightable.java //可以战斗的接口
         |      |--- Backgroud.java //背景
         |      |--- Location.java  //坐标
         |      |--- Thing2D.java   //所有图片元素的父类
         |      |--- Plat.java      //地图
         |      |--- ActionBean.java    //用于记录角色移动
         |      |--- Moveable.java  //表示图片元素可以移动
         |
         |---util   //工具
         |      |--- Log.java   //日志
         |      |--- Print.java //打印
         |      |--- Recorder.java  //记录器
         |      |--- DateUtil.java  //日期工具
         |      |--- Replayer.java  //回放器
         |
         |---view
         |      |--- Field.java     //主界面
         |      |--- Ground.java    //窗口
         |
         |---Main.java      //应用入口
```


### 代码详细说明
#### 1. 面向对象
1. Thing2D是在主界面所有图片显示的父类，如果想要在主界面显示一个图片，需要继承Thing2D；
2. 实现Moveable接口的类可以在主界面移动；
3. 实现Fightable接口的类可以与相同的类的对象战斗；
4. 每个角色通过角色工厂实例化；

#### 2. 游戏策略 
每个角色随机选择一个方向行走，但是向敌人移动的概率更大；每个角色都有自己的力量值，当不同阵营的两个角色小于一定距离时，它们选取一个概率决定双方生死，力量值大的更容易胜利；

#### 3. 多线程
每个角色都是一个线程，它们按照下面的流程循环(具体方法在Actor.java里)：     
    1. 检查一定距离内是否有敌人；    
    2. 如果有敌人则战斗；     
    3. 战斗失败结束线程，战斗胜利继续；     
    4. 选择一个方向前进一步；      
    5. 没有敌人则结束线程；