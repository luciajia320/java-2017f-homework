#### 1.角色
故事中的角色有葫芦娃、老爷爷、蛇精、蝎子精、以及一些小喽啰，它们都实现Creature接口：

(1) 对于七个葫芦娃、老爷爷、蛇精、蝎子精这几种角色，在故事中只允许存在一个对象，不允许类的client随意创建它们的实例，因此，Huluwa、OldMan、Snake、Scorpion这四个类的设计采用了单例模式，保证在程序运行期间只有一个实例, 但对于小喽啰的类，要求可以根据client的需求创建其实例

(2) 对于葫芦娃类，应当是七个角色都被创建的，为了体现这一点，并且降低其接口的复杂度，在Huluwa中定义了一个静态内部类HuluBrothers，该类的构造函数为private，只能在Huluwa中创建HuluBrothers的实例，结合单例模式，保证只有一个HuluBrothers存在



#### 2.场景
这个部分主要包括对Position和二维平面的定义

+Position是一个泛型类，有一个泛型成员holder，用来放Creature

+Arean描述的是二维平面，一个 N × N 的 Position矩阵，它的功能是负责保存平面信息以及设置指定位置的Position的holder

Position中没有其位置的坐标信息，因为我觉得Position只应该知道其中的holder是什么，坐标信息要放在Arean中才有意义

#### 3.阵法
抽象阵法Order ： 其中有特定阵法公用的构造函数，以及构造阵法需要的一些工具方法，另外，它包含一个很重要的抽象方法：

abstract List<Point> order()
  
它的作用是根据client的要求以及特定阵法的模板返回一个Point(其中有x和y坐标)的列表，即阵法应该填充的Position的列表，client设置好阵法参数(如方向，人数等等)之后，可以调用该方法获得Point列表，根据该列表用相应的角色去填充Arean的对应Position，这样设计的目的是实现解耦，阵法类的作用只是返回一个位置列表，而不是直接设置arean中的Positions

特定阵法LongSnake、Heyi和FengShi都继承自Order，重写了上述抽象方法，采用不同的策略来计算得到不同的Point列表
  
#### 4.Story类
Story类模拟的是一个故事，它的作用有初始化人物以及场景，并且像导演一样编写剧本，指示各种人物执行特定的任务，它有两个public方法：

Story()：在此方法中获得各个人物的实例并初始化二维平面

start(): 这个方法是故事的主要流程
