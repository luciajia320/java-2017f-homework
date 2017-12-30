# 说明文档
Wangxiz的Java课程作业三(20171010)说明文档。

## 版本更新记录

### V 1.4 — 2017.12.30
- 重构自定义异常类`FormationException`。增强可复用性
- 添加植物类`Plant`。修改继承结构
- 改写`RandomPlant`。使用泛型通配符
- 添加文件IO。将输出写入文件
- 添加文件IO，定义`XmlReader`类。从外部读入`XXXFormation`的阵法配置文件
- 更新README。添加 emoji 图标说明
- 更新README。添加 **泛型机制**、**IO** 小节
- 更新README。更新项目结构


### V 1.3 — 2017.12.29
- 更新README。增加项目整体类结构图
- 更新README。增加源代码说明、单元测试说明、自动构建说明
- 将`GrandPa`、`SnakeEssence`、`ScorpionEssence`重新实现为单例模式
- 将`FengShi`、`DongE`等类定义为`final`类
- 添加`MinionFactory`单例类，一次批量生成小喽啰
- 将`CalaCrops`、`EssenceCrops`重新实现为单例模式
- 添加`creature.plant`包，添加各种植物类型。在空间位置上没有动物时随机放置一种植物
- 使用 **RTTI** 信息在`RandomPlant`类中创建随机植物类对象实例
- 添加单元测试。对`RandomPlant`、`MinionFactory`等类中的部分方法进行单元测试
- 添加异常处理。自定义异常类`FormationException`，用于处理阵法位置超出空间`space`边界的情况
- 项目重构。在`create`包中新建`animal`包，将所有动物类迁移至其中

### V 1.2 — 2017.12.28
- 增加抽象基类`Crops`。重构`CalaCrops`、`EssenceCrops`
- 增加泛型机制。将`Position`改进为泛型
- 使用 Maven 构建项目。将原项目迁移至新的 Maven 项目
- 添加单元测试。对`Space`、`Crops`等类中的部分方法进行单元测试

### V 1.1 — 2017.12.27
- 更新README。插入图片
- 重新实现迭代器模式。基于 `Iterator` 和 `Iterable` 接口
- 增加变换阵法。**方円阵法**
- 修改类`BasicFormation`。提高代码可复用性
- 使用 Collection Framework 重写`BasicFormation`、`CalaCrops`、`EssenceCrops`

### V 1.0 — 2017.12.26
- 增加了两军对阵
- 爷爷和蛇精助阵
- 提供多种阵法


## 葫芦山奇妙物语
>本故事纯属虚构，如有雷同，纯属巧合。

### 0. 楔子
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
>![](https://raw.githubusercontent.com/Wangxiz/java-2017f-homework/master/20171010/%E7%8E%8B%E7%86%99%E7%81%B6-141220105/jpgs/r-scene1.JPG)

### 3. 整队
葫芦兄弟见那蛇蝎派未到，正好借机修整队伍。
>![](https://raw.githubusercontent.com/Wangxiz/java-2017f-homework/master/20171010/%E7%8E%8B%E7%86%99%E7%81%B6-141220105/jpgs/r-scene2.JPG)

### 4. 魔至
葫芦娃们整队完毕后，蛇蝎派此时也匆忙赶到。

却见众魔中，只见那蝎子精和一众小妖，独独不见那蛇精的身影。

蝎子精带领众小妖，摆出了一个锋矢阵法，与葫芦娃对峙。
>![](https://raw.githubusercontent.com/Wangxiz/java-2017f-homework/master/20171010/%E7%8E%8B%E7%86%99%E7%81%B6-141220105/jpgs/r-scene3.JPG)

### 5. 助阵
摆阵之后，蝎子精便再无举动，也不进攻，只是在那边警惕地看着七只葫芦娃，似乎在等什么。

对峙良久，却见远处吹来一阵黑风。到眼前，化为了蛇精的模样。那蛇精脸上，只见是傅粉施朱，好一番精心打扮。

此时，爷爷也赶到了端星台，为娃娃们助阵。
>![](https://raw.githubusercontent.com/Wangxiz/java-2017f-homework/master/20171010/%E7%8E%8B%E7%86%99%E7%81%B6-141220105/jpgs/r-scene4.JPG)

### 6. 魔动
那蝎子精见蛇精赶到，便开始作妖。

首先，变换了阵法，动轭阵法。
>![](https://raw.githubusercontent.com/Wangxiz/java-2017f-homework/master/20171010/%E7%8E%8B%E7%86%99%E7%81%B6-141220105/jpgs/r-scene5.JPG)

那蝎子精似乎是想示威，转而再次变换了阵法，鹤翼阵法。
>![](https://raw.githubusercontent.com/Wangxiz/java-2017f-homework/master/20171010/%E7%8E%8B%E7%86%99%E7%81%B6-141220105/jpgs/r-scene6.JPG)

须臾，蝎子精又再次变换了阵法，方円阵法。
>![](https://raw.githubusercontent.com/Wangxiz/java-2017f-homework/master/20171010/%E7%8E%8B%E7%86%99%E7%81%B6-141220105/jpgs/r-scene7.JPG)

### ……
欲知后事如何，请听下回分解。

## 设计理念
### 继承思想
1、生物体基类`Creature`。葫芦娃类`Calabash`、蝎子精类`ScorpionEssence`、蛇精类`SnakeEssence`、爷爷类`GrandPa`、小喽啰类`Minion`均继承于此。

2、阵法基类`BasicFormation`。动轭阵法类`DongE`、方円阵法类`FangYuan`、锋矢阵法类`FengShi`、鹤翼阵法类`HeYi`、长蛇阵法类`LongSnake`等均继承于此。

3、军团基类`Crops`。葫芦军团`CalaCrops`和妖精军团`EssenceCrops`均继承于此。

4、`creature.plant`中的十种植物类均继承于生物体基类`Creature`，用于表示空间位置上的空白位置。

### 聚合思想
1、葫芦娃战队类`CalaCrops`。聚合了七只葫芦娃。

2、妖魔战队类`EssenceCrops`。聚合了蝎子精和六只小喽啰。

3、空间类`Space`。由 N*N 个`Position`构成。

### 多态思想
1、在`Position`类定义中，`Holder`成员的类型定义为`Creature`，实际的`Holder`可能为其派生类：葫芦娃`Calabash`、蝎子精`ScorpionEssence`、蛇精`SnakeEssence`、爷爷`GrandPa`、小喽啰`Minion`。

2、葫芦战队和妖魔战队设定阵法的函数参数使用的都是`BasicFormation`。实际设定阵法时，以相应的派生类对象代入其中。

### 设计模式
~~阵法类中，使用了迭代器设计模式的思想，从阵法的阵头初始位置，使用`Next()`依次获得下一个位置。~~

1、阵法类中，使用了迭代器设计模式。在阵法基类中实现了 `Iterable<Position<Creature>>`接口，定义了一个实现了`Iterator<Position<Creature>>`接口的内部类。

2、葫芦娃战队`CalaCrops`和妖魔战队`EssenceCrops`等类中，使用了单例设计模式，表示其唯一性。

3、工厂模式。`CalabashFactory`和`MinionFactory`使用了工厂模式，一次性构造葫芦娃和小喽啰。

### 泛型机制
1、`Position`类中，定义泛型为`T extends Creature`，表示`Position`的`Holder`是某种`Creature`类型。

2、`RandomPlant`类中，将`plants`定义为`ArrayList<Class<? extends Plant>>`，表示该`List`中的`element`都是`Plant`或其派生类的`Class`对象。

### RTTI
在`RandomPlant`类中，定义了一个`Class`对象数组，数组元素为上述十种植物类的`**.class`。

### IO
1、在`TestMain`中使用文件输出，将运行结果输出至文件中

2、定义`XmlReader`类。将`formation`包中定义的各阵法的阵型配置存于 xml 文件中，使用`XmlReader`从文件中读取阵型配置。

## 项目结构
### 整体结构图
>![](https://raw.githubusercontent.com/Wangxiz/java-2017f-homework/master/20171010/%E7%8E%8B%E7%86%99%E7%81%B6-141220105/jpgs/structure.png)

### 源代码说明
#### Package `creature`
Class `Creature`：代表生物体的基类。每个生物体拥有一个`Position`属性，代表其位置。还有相应的操作`Position`的`getPosition`和`setPosition`方法。

#### Package `creature.animal`
1、Class `Calabash`：代表葫芦娃的类，继承自`Creature`。每个葫芦娃有两个属性：`order`和`color`，分别表示其长幼次序和颜色，有相应的`get`和`set`方法。另外`Calabash`还实现了`Comparable`接口，根据长幼次序比较大小。在其中还定义了两个枚举类型：`Color`、`Order`，对应于表示葫芦娃的两个属性。

2、Class `CalabashFactory`：葫芦娃的单例工厂类。其中包含`get(int i)`方法，表示返回第几个葫芦娃。

3、Class `GrandPa`：代表爷爷的类。使用单例设计模式。

4、Class `Minion`：代表小喽啰的类。

5、Class `MinionFactory`：小喽啰的单例工厂类。其中包含`get(int i)`方法，表示返回第几个小喽啰。

6、Class `SnakeEssence`：代表蛇精的类。使用单例设计模式。

7、Class `ScorpionEssence`：代表蝎子精的类。使用单例设计模式。

8、Class `Crops`：表示一个战队的抽象基类。战队有一个属性`basicFormation`，表示战队当前使用的阵法；其中包含一个抽象方法`setFormation`，用于为战队设定当前使用的阵法；还包含一个`clearFormation`方法，用于取消当前使用的阵法。

9、Class `CalaCrops`：葫芦娃战队类，继承自`Crops`类。由七只葫芦娃构成。实现了`Crops`中的抽象方法。提供了`shuffle`和`sort`方法，用于将葫芦娃战队顺序打乱和排序。还提供了`get(int i)`方法，返回战队中的第`i`个葫芦娃。

10、Class `EssenceCrops`：妖魔战队类，继承自`Crops`类。由一只蝎子精和六只小喽啰构成。实现了`Crops`中的抽象方法。

#### Package `creature.plant`
1、Class `Plant`：植物基类，继承自`Creature`类。有两个属性：`plantName`表示植物名称，`plantEmoji`表示植物的 emoji 图标。

2、包含以下十种植物类，均继承自`Plant`类：
- Class `FourLeafClover`：four leaf clover
- Class `Mushroom`：mushroom
- Class `MapleLeaf`：maple leaf
- Class `Blossom`：blossom
- Class `SunFlower`：sun flower
- Class `Tulip`：tulip
- Class `PalmTree`：palm tree
- Class `Rose`：rose
- Class `Cactus`：cactus
- Class `Hibiscus`：hibiscus

3、Class `RandomPlant`：随机植物类。静态方法`get()`从以上十种植物中，随机返回一种植物对象实例。使用了RTTI信息。

#### Package `formation`
1、Class `BasicFormation`：阵法基类。`BasicFormation`有四个属性，`current_x`表示阵头的x方向坐标，`current_y`表示阵头的y方向坐标，`space`表示阵法所处的空间，`positions`则表示阵法的空间位置集合，每个阵法由七个阵法位置构成。`clear`方法取消当前阵法中每个位置上的生物与阵法位置的关联。`BasicFormation`实现了`Iterable`接口，为阵法位置提供了迭代器访问方式。

2、Class `DonE`：动轭阵法类，继承自`BasicFormation`类。

3、Class `FangYuan`：方円阵法类，继承自`BasicFormation`类。

4、Class `FengShi`：锋矢阵法类，继承自`BasicFormation`类。

5、Class `HeiYi`：鹤翼阵法类，继承自`BasicFormation`类。

6、Class `LongSnake`：长蛇阵法类，继承自`BasicFormation`类。

#### Package `formation.exception`
Class `FormationException`：自定义异常类。用于处理阵法中某个位置超出空间`space`边界的情况

#### Package `formation.util`
1、Class `XmlReader`：xml 配置文件读取器类。用于读取 xml 格式的阵法配置文件。

2、Class `Point`：用于存储从 xml 文件中读取的阵法位置信息。

#### Package `space`
1、Class `Position`：表示位置的类。使用泛型机制，其上有一个泛型类型的属性`Holder`，表示这个位置上的物体。提供了`Holder`的`get`和`set`方法。

2、Class `Space`：表示二维空间的类。`Space`有一个属性`size`，表示其大小，`Space`由 `size` * `size`个`Position`构成。其上有个方法`creature_position_setter`，用于将生物体与空间位置关联起来。还有相应的`Pos`和`size`的`get`方法。`Space`类重载了`toString`方法，用于输出空间上每个位置上的生物体情况。

#### Class `TestMain`
主类。

### 单元测试
#### Package `create.animal`
1、Class `CalabashFactoryTest`：测试`getInstance()`、`get()`方法

2、Class `CalaCropsTest`：测试`getInstance()`、`sort()`、`setXXXFormation()`方法

3、Class `CropTest`：测试`clearFormation()`方法

4、Class `EssenceCropsTest`：测试`getInstance()`、`setXXXFormation()`方法

5、Class `MinionFactoryTest`：测试`getInstance()`、`get()`方法

#### Package `create.plant`
1、Class `RandomPlantTest`：测试`get()`方法

#### Package `space`
1、Class `SpaceTest`：测试`creature_position_setter()`方法

## 自动构建
本项目使用的自动构建工具为 **Maven**，版本 **3.5.2**
- **mvn package**：自动打包项目。其中，`target`目录下的`Assignment03-1.3.jar`是不指定主类版本，`Assignment03-1.3-jar-with-dependencies.jar`是指定主类版本
- **mvn clean**：自动清理项目。删除`target`目录
- **mvn test**：自动测试。调用项目中的所有单元测试方法进行测试

## 说明
每种阵法**只能由七人**才能施展，多一人不可，少一人亦不行。

### emoji 图标说明
大娃：😎 二娃：😀 三娃：😛 四娃：😮 五娃：😐 六娃：😙 七娃：😟<br>
爷爷：👴<br>
蛇精：🐍 蝎子精：🦂 小喽啰：🐒<br>
植物图标：🌼 🌵 🍀 🌺 🍁 🍄 🌴 🌹 🌻 🌷
