# 第三次作业设计说明


### 面向对象概念
* 使用基类Creature和Formation分别代表生物和阵型。
* 使用接口Sorter和Comparable。
* 在存储Ground对象时使用了范型队列。

### 面向对象机制
* 使用了继承机制：  
基类Creature有衍生类Huluwa、Grandpa、Scorpion、Snake、Louluo。  
基类Formation有衍生类Queue、HeYi、YanXing。
* 使用了接口机制：  
BubbleSorter类implements Sorter类。  
Huluwa类implements Comparable类。
* 使用了组合机制：  
Formation类中组合了Position[]用来记录生物的位置。
* 使用了聚集机制：  
Formation类中聚集了Creature[]，引用了应该排列的生物。
Ground类中聚集了Position[][]，代表了n*n的场地，在有生物的位置引用了Formation中的Position值。

### 设计理念
* 整体的设计思路是将现实中的生物、位置、阵型、场地对象映射到程序中形成类Creature、Position、Formation、Ground。
* 遵循单一职责原则，Sorter接口负责排序，Creature类负责报告自己，Position类负责承载生物以及记录自己的位置，Formation类负责安排生物的相对位置，Ground类负责布置阵型以及显示。
* Creature基类和Formation基类将大部分的共同职责抽象出来，使得衍生类只需要具体实现toString()和arrange()方法。

### 设计优点
* 从分类学的角度将现实的各个对象抽象出来，使得类之间弱耦合，各自的职责划分清晰。
* 基类以及接口的设置便于代码复用。
