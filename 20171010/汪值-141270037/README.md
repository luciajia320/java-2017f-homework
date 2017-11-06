# 面向对象设计说明

###封装

1. 类的内部数据使用`private`修饰，提供`getter`和`setter`方法, 对外隐藏实现.
2. 类的方法使用`public` 修饰, 对外开放服务.

### 继承与多态

1. 所有的生物都是继承了`Creature` 接口.
2. 葫芦娃类实现了`Comparable`接口，可以调用`compareTo`方法进行比较操作.
3. `QuickSorter`和`BubbleSorter`实现了`Sorter`接口, 拥有良好的扩展性。

 