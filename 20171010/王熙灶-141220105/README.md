# 说明文档
Wangxiz的Java课程作业三(20171010)说明文档。

## 版本更新记录

### v1.1 — 2017.12.27
- 更新README，插入图片
- 基于 Iterator 和 Iterable 接口，重新实现迭代器模式
- 增加变换阵法，**方円阵法**

### v1.0 — 2017.12.26
- 增加了两军对阵
- 爷爷和蛇精助阵
- 提供多种阵法


## 葫芦山奇妙物语
### 0. 楔子
>本故事纯属虚构，如有雷同，纯属巧合。

话说天地初开，盘古开天辟地后，力竭而亡，化为了葫芦山。

葫芦山上，凭藉盘古大神留下的仙气，物种生生代代，繁衍不息，同时也在不断地进化，随之带来的欲念——贪嗔痴恨，也使得一批物种逐渐堕落，坠入魔道。不知多少年后，魔道中的一支——蛇蝎派，逐渐强大。蛇蝎派由蛇精、蝎子精两大魔头统领，手下有一批小喽啰。

而葫芦山的守护者——葫芦兄弟，以及敬爱的老爷爷，眼见邪魔当道，心中不忿，发誓定当斩妖除魔，匡扶正义。

### 1. 约战
蛇蝎派听闻葫芦兄弟发誓要除掉自己，顿时大怒。

派了一个小喽啰前来叫阵，约定明日卯时，在葫芦山的端星台，决一高下。

### 2. 懒觉
葫芦兄弟前日夜间商讨决战计策，却不想第二日睡过了头。

七只葫芦娃按长蛇阵法摆阵，爷爷走得慢，七只娃便先行一步，匆匆忙忙赶往端星台，队伍排得那叫一个乱七八糟。

到端星台时，却发现那蛇蝎派也尚未到场。
>![](https://raw.githubusercontent.com/Wangxiz/java-2017f-homework/master/20171010/%E7%8E%8B%E7%86%99%E7%81%B6-141220105/jpgs/scene1.JPG)

### 3. 整队
葫芦兄弟见那蛇蝎派未到，正好借机修整队伍。
>![](https://raw.githubusercontent.com/Wangxiz/java-2017f-homework/master/20171010/%E7%8E%8B%E7%86%99%E7%81%B6-141220105/jpgs/scene2.JPG)

### 4. 魔至
葫芦娃们整队完毕后，蛇蝎派此时也匆忙赶到。

却见众魔中，只见那蝎子精和一众小妖，独独不见那蛇精的身影。

蝎子精带领众小妖，摆出了一个锋矢阵法，与葫芦娃对峙。
>![](https://raw.githubusercontent.com/Wangxiz/java-2017f-homework/master/20171010/%E7%8E%8B%E7%86%99%E7%81%B6-141220105/jpgs/scene3.JPG)

### 5. 助阵
摆阵之后，蝎子精便再无举动，也不进攻，只是在那边警惕地看着七只葫芦娃，似乎在等什么。

对峙良久，却见远处吹来一阵黑风。到眼前，化为了蛇精的模样。那蛇精脸上，只见是傅粉施朱，好一番精心打扮。

此时，爷爷也赶到了端星台，为娃娃们助阵。
>![](https://raw.githubusercontent.com/Wangxiz/java-2017f-homework/master/20171010/%E7%8E%8B%E7%86%99%E7%81%B6-141220105/jpgs/scene4.JPG)

### 6. 魔动
那蝎子精见蛇精赶到，便开始作妖。

首先，变换了阵法，动轭阵法。
>![](https://raw.githubusercontent.com/Wangxiz/java-2017f-homework/master/20171010/%E7%8E%8B%E7%86%99%E7%81%B6-141220105/jpgs/scene5.JPG)

那蝎子精似乎是想示威，转而再次变换了阵法，鹤翼阵法。
>![](https://raw.githubusercontent.com/Wangxiz/java-2017f-homework/master/20171010/%E7%8E%8B%E7%86%99%E7%81%B6-141220105/jpgs/scene6.JPG)

须臾，蝎子精又再次变换了阵法，方円阵法。
>![](https://raw.githubusercontent.com/Wangxiz/java-2017f-homework/master/20171010/%E7%8E%8B%E7%86%99%E7%81%B6-141220105/jpgs/scene7.JPG)

### ……
欲知后事如何，请听下回分解。

## 设计理念
### 继承思想
1、生物体基类`Creature`。葫芦娃类`Calabash`、蝎子精类`ScorpionEssence`、蛇精类`SnakeEssence`、爷爷类`GrandPa`、小喽啰类`Minion`均继承于此。

2、阵法基类`BasicFormation`。动轭阵法`DongE`、方円阵法`FangYuan`、锋矢阵法`FengShi`、鹤翼阵法`HeYi`、长蛇阵法`LongSnake`等均继承于此。

### 聚合思想
1、葫芦娃战队类`CalaCrops`。聚合了七只葫芦娃。

2、妖魔战队类`EssenceCrops`。聚合了蝎子精和六只小喽啰。

### 多态思想
1、在`Position`类定义中，`Holder`成员的类型定义为`Creature`，实际的`Holder`可能为其派生类：葫芦娃`Calabash`、蝎子精`ScorpionEssence`、蛇精`SnakeEssence`、爷爷`GrandPa`、小喽啰`Minion`。

2、葫芦战队和妖魔战队设定阵法的函数参数使用的都是`BasicFormation`。实际设定阵法时，以相应的派生类对象代入其中。

### 设计模式
~~阵法类中，使用了迭代器设计模式的思想，从阵法的阵头初始位置，使用`Next()`依次获得下一个位置。~~

阵法类中，使用了迭代器设计模式。在阵法基类中实现了  `Iterable<Position>`接口，定义了一个实现了`Iterator<Position>`接口的内部类。

## 说明
每种阵法**只能由七人**才能施展，多一人不可，少一人亦不行。
