## 葫芦娃编程作业3

----

### 程序结构说明
```
./src/
├───wz
    ├───creature
        ├───生物类: Creature Grandpa Huluwa Monster Vacancy...
    ├───form
        ├───阵型类: Form FormChangShe FormHeyi FormYanxing
    ├───position
        ├───场地: Position RectField
    ├───sorter
        ├───排序: Sorter BubbleSorter QuickSorter
    ├───主程序:Application.java
```

----

### 面向对象设计说明

- **封装:** 类的内部数据使用`private` 修饰，提供`getter` 和`setter` 方法, 对外隐藏实现, 类的方法使用`public` 修饰, 对外开放服务.

- **多态:** 所有的生物都是继承了`Creature` 接口, 葫芦娃类实现了`Comparable` 接口，可以调用`compareTo` 方法进行比较操作, `QuickSorter` 和`BubbleSorter` 实现了`Sorter` 接口, 拥有良好的扩展性.

----

### 设计原则

- **SRP:** 一个类负责一种功能, 如Creature负责定义各种生物, Form定义各种阵型, Sorter定义排序器, Application为主程序

- **OCP:** 各种生物可以扩充Creature而不需要修改Creature, 对扩展开放, 对修改封闭

- **LSP:** Sorter是各种排序器的父接口, 在使用Sorter的地方可以用BubbleSorter和QuickSorter代替, 同理Form和Creature一样

----

### 输出结果

```
对峙局面: 
口 口 口 口 口 口 口 口 口 口 口 口 口 
口 大 口 口 口 蝎 口 口 口 口 口 喽 口 
口 二 口 口 口 口 喽 口 口 口 喽 口 口 
口 三 口 口 口 口 口 喽 口 喽 口 口 口 
口 四 口 口 口 口 口 口 喽 口 口 口 口 
口 五 口 口 口 口 口 口 口 口 口 口 口 
口 六 口 口 口 口 口 口 口 口 口 口 口 
口 七 爷 口 口 口 口 口 蛇 口 口 口 口 
口 口 口 口 口 口 口 口 口 口 口 口 口 

变换阵型, 继续对峙: 
口 口 口 口 口 口 口 口 口 口 口 口 口 
口 大 口 口 口 蝎 口 口 口 口 口 口 口 
口 二 口 口 口 口 喽 口 口 口 口 口 口 
口 三 口 口 口 口 口 喽 口 口 口 口 口 
口 四 口 口 口 口 口 口 喽 口 口 口 口 
口 五 口 口 口 口 口 口 口 喽 口 口 口 
口 六 口 口 口 口 口 口 口 口 喽 口 口 
口 七 爷 口 口 口 口 口 蛇 口 口 喽 口 
口 口 口 口 口 口 口 口 口 口 口 口 口 

再次变换阵型对峙: 
口 口 口 口 口 口 口 口 口 口 口 口 口 
口 大 口 口 口 蝎 口 口 口 口 口 喽 口 
口 二 口 口 口 口 喽 口 口 口 喽 口 口 
口 三 口 口 口 口 口 喽 口 喽 口 口 口 
口 四 口 口 口 口 口 口 喽 口 口 口 口 
口 五 口 口 口 口 口 口 口 口 口 口 口 
口 六 口 口 口 口 口 口 口 口 口 口 口 
口 七 爷 口 口 口 口 口 蛇 口 口 口 口 
口 口 口 口 口 口 口 口 口 口 口 口 口 
```



