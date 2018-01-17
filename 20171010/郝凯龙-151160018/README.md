#实验报告
郝凯龙 151160018

##一、类设计
###Creature 生物
是一个抽象类，定义了抽象方法getName()，用于获得生物的名称。

###Hulu 葫芦娃
继承于Creature类，实现四种方法
1.String getName() 返回值为"Hulu"
2.int getKey() 给出葫芦娃的排行
3.String getColor() 给出葫芦娃的颜色
4.String getRank() 给出葫芦娃的排行

###Grandpa 爷爷
继承于Creature类，实现两种方法
1.String getName() 返回值为"Grandpa"
2.void sort(Hulu array[]) 用于帮助葫芦娃排好队

###Soldier 小喽罗
继承于Creature类，实现一种方法
String getName() 返回值为"Soldier"

###Snake 蛇精
继承于Creature类，实现一种方法
String getName() 返回值为"Snake"

###Scorpion 蝎子精
继承于Creature类，实现一种方法
String getName()返回值为"Scorpion"

###Space 空间
实现了以下几种方法：
1.Space(int n) 用于构造一个n*n的空间，其中每个空间均为Creature类型
2.void IFormation(Creature array[],int n) 用于根据array布长蛇阵
3.void AFormation(Creature array[],int n) 用于根据array布锋矢阵
4.void VFormation(Creature array[],int n) 用于根据array布鹤翼阵
5.void show() 用于输出战况

##二、面向对象
1.用到了类的继承，所有的角色均继承于生物（Creature），体现了类之间的关系，所有的角色均是生物的子类型
2.用到了抽象类，生物（Creature)定义为抽象类，主要用于给出框架，具体的实现则不同的子类各自不同

##三、心得体会
1.更加熟悉了Java语言：~Java中的方法必须在类中定义，不可以脱离类而存在；Java中不需要析构函数，由JVM进行垃圾回收；Java中的空值为null~
2.面向对象更加熟悉，即将现实中的东西直接映射为对象。
