设计理念
=====
*****
1.首先创建一个creature类，作为所有生命体的父类。并继承产生子类：Huluwa,Minion,Grandfather,QueenSnake

2.创建地图，map类，编写map的初始化以及打印地图的函数report_map();

3.编写各个方阵各自的类，完成题目的要求

4.为了方便在各个阵型中转换，而不影响程序的实现，我编写了一个FormationClear类，在每次执行完一个阵型后，自动清理map


*******

继承
====
1.Huluwa,QueenSnake,Grandpa,Minion,都继承自Creature类，Creature类中有report_status（）函数，打印各自的名称

2.map中是Position类型的N*N的正方形地图，每个Position中或者为空或者存放一个Creature类型的对象
