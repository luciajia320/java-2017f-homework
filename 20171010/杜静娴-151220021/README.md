# Homework3 11/10

> #设计思路

- `Creature`是一个公共接口，所有生物都实现了`Creature`接口，`BattleField`是战斗发生的场地，`Formation`是阵型，具有排兵布阵的能力和指责。
- 首先在`BattleField`里添加所有生物，使用它们添加队形，然后删除队形重新添加。

> #概念、机制、设计原则

## 抽象&封装

- `Creature`接口：生物的抽象，描述了共同的方法，如获取位置、头像、报数等。`Huluwa`等具体的生物类各自实现其接口。对于其他类，只提供生物类的公共方法，而不提供具体代码。
- `Sorter`接口：是排序功能的接口，只提供一个`sort`方法。
- `Formation`基类：提供列队的功能

## 继承

- `Formation`基类：`CraneWingForm`、`SnakeForm`、`GooseForm`等子类继承于基类`Formation`
- `Creature`接口：`Huluwa`等子类
- `Sorter`接口：`InsertionSorter`等子类

## 多态

- 对于存放生物的队形`Formation`来说，每一个位置上的生物都是`Creature`，但是调用其获取头像、报数等方法的结果因`Creature`的运行时真实类型不同而不同。
- 对于`BattleField`来说，放置了一些`Formation`，`addFormation`方法中只需要把这些`Formation`中的内容显示到屏幕上即可，但如`CraneWingForm`这些子类才是运行时的真正类型，实际上由`CraneWingForm`这些真正类型来告诉`BattleField`队列真正的样子。

##设计原则

- SRP单一指责

  `Sorter`只承担对传入参数按照进行排序的职责，`Formation`只承担按指定队列将生物排序的指责，`Creature`只承担返回头像、位置、报数等指责。

- OCP开放封闭

  每增加一种新的`Creature`只需要继承`Creature`接口，重写接口的实现，而不需要修改`Creature`的代码

- LSP里氏替换

  每一种实际的`Creature`在类外部的使用中都用`Creature`来标识，即可以完全用`Creature`替代

- ISP接口隔离原则

  `Creature`只需要知道询问头像的时候返回什么头像，询问位置的时候返回什么位置，而不需要知道如何按照`CraneWingForm`排序，这是`CraneWingForm`需要实现的接口

- DIP依赖倒置原则

  具体的生物都实现并依赖于`Creature`接口，不需要`Creature`去询问运行时的具体类

- LOD迪米特法则

  生物类有自己具体的属性，但只对外提供获得属性的方法，但不能直接获取