# "葫芦娃大战妖精" 说明文档
Wangxiz的 Java 大作业说明文档。

## 版本更新记录

### Final Project V 1.5 (final version)
#### 更新时间
- 2018.01.08

#### 更新内容
- ~~TODO：每个时间点记录数据~~  **Done**
- ~~TODO：根据读取到的存档，完成游戏回放功能~~ **Done**
- 修复游戏结束按 Reset 按钮无反应 bug
- 游戏结束后自动存档，并记录文档编号。用户可使用 Save 菜单选择另存文件
- 修改存档格式，删除冗余元素
- 解决当前游戏的存档，在本轮结束之后不能读取的问题
- 添加单元测试
- UPDATE README

### Final Project V 1.4
#### 更新时间
- 2018.01.07

#### 更新内容
- ~~TODO：将 `Enum State` 改为英文枚举，中文字符串~~ **Done**
- 将界面语言设置为 English
- ~~TODO：解决欢迎界面按钮鼠标扫过才显示问题~~ **Solved**
- 修改欢迎界面按钮，设为图标按钮
- 修改欢迎界面背景
- ~~TODO：修复多线程bug问题~~ **Solved**
- ~~TODO：将所有生物体的信息输出到状态栏~~ **Done**
- 添加游戏模式，指示是真实游戏还是存档回放


### Final Project V 1.3
#### 更新时间
- 2018.01.06

#### 更新内容
- 添加打开文件和保存文件功能框架
- 实现 `ArchiveFilter` 过滤文件类型
- 定义存档文件格式 `.acv`
- 添加存档读取器 `ArchiveReader`
- 修改文件读取方式，解决直接执行jar包时文件读取错误的问题
- 完善右侧控制面板 + 状态面板
- 重构`class Crops`及其派生类
- 添加存档IO `class ArchiveIO`
- 将文件选择对话框与 `ArchiveIO`交互
- 保存文件时若文件已存在提示是否覆盖

### Final Project V 1.2
#### 更新时间
- 2018.01.05

#### 更新内容
- 更新README。修改内容结构
- 更新README。增加游戏说明
- 修改游戏初始界面
- 更新空间位置部分相关代码
- 添加全局静态变量包装类Constant
- 为`Creature.plant`包中所有类加注解`@Deprecated`
- 添加菜单栏：Run、Help
- 为菜单项添加图标
- 为菜单项添加快捷方式
- 为 Jframe 添加图标
- 设置全局 UI，统一字体
- 添加 About 界面
- 添加 Help 界面
- 多线程调 bug 中

### Final Project V 1.1
#### 更新时间
- 2017.12.31

#### 更新内容
- 更新README
- 迁移 **作业3** 代码至大作业中复用
- 添加动物基类 `Animal`，继承自`Creature`。在其中定义了`state`、`imageAlive`、`imageDead`属性
- 在`CalaCrops`和`EssenceCrops`中实现`Iterable`接口
- 添加游戏初始界面
- 添加菜单栏

### Final Project V 1.0
#### 更新时间
- 2017.12.30

#### 更新内容
- 搭建基本GUI界面
- 在界面上测试绘制图片


## 游戏说明
### 两军战队
- 葫芦娃战队：七色葫芦娃
- 妖魔战队：蝎子精 + 六只小喽啰

### 战队鼓励师
- 葫芦娃战队：老爷爷
- 妖魔战队：蛇精

### 游戏场景
#### 第一幕：对阵伊始
初始时，双方处于对阵状态。

#### 第二幕：战鼓响
战鼓响彻云霄，对战双方开始激战。此时战队鼓励师在旁边为己方加油呐喊助威。

#### 第三幕：英雄末路
只见双方鏖战许久，不分胜负。两军的鼓励师也不能坐以待毙，遂加入战场，与敌方死战。

#### 第四幕：鸣金收兵
最终，胜者为王，葫芦山霸主千秋万代。

## 操作说明

### 1. 欢迎界面
如下：
![](https://raw.githubusercontent.com/Wangxiz/java-2017f-homework/master/Fianl%20Project/%E7%8E%8B%E7%86%99%E7%81%B6-141220105/images/2.png)

欢迎界面上有三个按钮。
- **Enter**。点击可以进入游戏界面。
- **RePlay**。点击进入游戏界面并直接跳出文件选择框选择存档进行回放。
- **Help**。跳出帮助界面。

### 2. 游戏界面
如下：
![](https://raw.githubusercontent.com/Wangxiz/java-2017f-homework/master/Fianl%20Project/%E7%8E%8B%E7%86%99%E7%81%B6-141220105/images/1.png)

1、 左边为 9 * 12 的**游戏世界**，右侧上方是**控制台**，下方是**消息栏**。

2、控制台有三个按钮：
- Start。只在`GAME`模式下有效。控制游戏开始
- Stop。只在`GAME`模式下有效。控制游戏暂停
- Reset。在`GAME`模式下控制游戏恢复初始状态；在`REPLAY`模式下控制游戏模式重新设置为`GAME`，并恢复初始状态

### 3. 游戏模式
游戏设有两个模式：
- `GAME`模式。随机自动游戏模式
- `REPLAY`模式。回放存档模式

#### I. `GAME`模式
初始，只有 `Start` 按钮可用。

按下`Start`之后，`Start`按钮不可用，`Stop` 可用，`Reset` 可用；

按下 `Stop` 按钮之后，`Start` 可用，`Reset` 可用；

按下 `Reset` 按钮之后，`Start` 可用，`Reset` 和 `Stop` 不可用。

游戏结束时，系统会自动保存当前游戏的存档。

#### II. `REPLAY`模式
从菜单栏里选择`Open`或者从欢迎界面直接进入`REPLAY`模式。会回放所选存档的游戏记录。

在此模式下，当处于回放状态时，控制面板内三个按钮均处于`Disable`的状态；回放结束后，`Reset`按钮处于`Enable`状态，用于将游戏模式重新设置为`GAME`。

### 4. 游戏设定
1、生物体战死后，尸体留在原位置，并且该位置不能再被其他生物体占有；

2、按下`Start`之后，所有生物开始随机走动，处于战斗状态；爷爷和蛇精初始某段时间保持加油状态。两方生物相遇时，简单起见，以五五开的概率随机阵亡一个。

3、某方全部阵亡之后，对方获胜。

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

4、迭代器设计模式。在战队基类`Crops`的定义中声明了对`Iterable<Animal>`接口的实现，但其作为抽象类并没有实现该接口，而是由其两个派生类`CalaCrops`和`EssenceCrops`分别进行了实现。

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
>![](https://raw.githubusercontent.com/Wangxiz/java-2017f-homework/master/Fianl%20Project/%E7%8E%8B%E7%86%99%E7%81%B6-141220105/images/Welcome.png)

### 源代码说明
#### Pavkage `archive`
Class `ArchiveRecorder`：存档记录器。用于将每一个时间点的信息累计起来。

Class `CreatureArchived`：每一个生物体的存档信息。

Class `TimePoint`：一个时间点的存档信息。

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

#### ~~Package `creature.plant`~~
~~1、Class `Plant`：植物基类，继承自`Creature`类。有两个属性：`plantName`表示植物名称，`plantEmoji`表示植物的 emoji 图标。~~

~~2、包含以下十种植物类，均继承自`Plant`类：~~
- ~~Class `FourLeafClover`：four leaf clover~~
- ~~Class `Mushroom`：mushroom~~
- ~~Class `MapleLeaf`：maple leaf~~
- ~~Class `Blossom`：blossom~~
- ~~Class `SunFlower`：sun flower~~
- ~~Class `Tulip`：tulip~~
- ~~Class `PalmTree`：palm tree~~
- ~~Class `Rose`：rose~~
- ~~Class `Cactus`：cactus~~
- ~~Class `Hibiscus`：hibiscus~~

3、~~Class `RandomPlant`：随机植物类。静态方法`get()`从以上十种植物中，随机返回一种植物对象实例。使用了RTTI信息。~~

#### Package `formation`
1、Class `BasicFormation`：阵法基类。`BasicFormation`有四个属性，`current_x`表示阵头的x方向坐标，`current_y`表示阵头的y方向坐标，`space`表示阵法所处的空间，`positions`则表示阵法的空间位置集合，每个阵法由七个阵法位置构成。`clear`方法取消当前阵法中每个位置上的生物与阵法位置的关联。`BasicFormation`实现了`Iterable`接口，为阵法位置提供了迭代器访问方式。

2、Class `DonE`：动轭阵法类，继承自`BasicFormation`类。

3、Class `FangYuan`：方円阵法类，继承自`BasicFormation`类。

4、Class `FengShi`：锋矢阵法类，继承自`BasicFormation`类。

5、Class `HeiYi`：鹤翼阵法类，继承自`BasicFormation`类。

6、Class `LongSnake`：长蛇阵法类，继承自`BasicFormation`类。

#### Package `exception`
Class `FormationException`：自定义异常类。用于处理阵法中某个位置超出空间`space`边界的情况

#### Package `space`
1、Class `Position`：表示位置的类。使用泛型机制，其上有一个泛型类型的属性`Holder`，表示这个位置上的物体。提供了`Holder`的`get`和`set`方法。

2、Class `Space`：表示二维空间的类。`Space`有一个属性`size`，表示其大小，`Space`由 `size` * `size`个`Position`构成。其上有个方法`creature_position_setter`，用于将生物体与空间位置关联起来。还有相应的`Pos`和`size`的`get`方法。`Space`类重载了`toString`方法，用于输出空间上每个位置上的生物体情况。

#### Package `ui`
1、Class `AboutFrame`：关于界面。

2、Class `ControlPanel`：控制面板。

3、Class `Ground`：战场界面。

4、Class `HelpFrame`：帮助界面。

5、Class `HuluMountainFrame`：主界面Frame。

6、Class `MenuBar`：菜单栏。

7、Class `StatusBar`：消息栏。

8、Class `Welcome`：欢迎界面。

#### Package `util`
1、Class `FormationReader`：xml 配置文件读取器类。用于读取 xml 格式的阵法配置文件。

2、Class `Point`：用于存储从 xml 文件中读取的阵法位置信息。

1、Class `ArchiveIO`：存档IO工具。

1、Class `Constant`：静态变量工具类。

1、Class `Direction`：方向枚举类型。有UP, DOWN, LEFT, RIGHT, UPLEFT, UPRIGHT, DOWNLEFT, DOWNRIGHT 八个方向。

1、Enum `GameMode`：游戏模式枚举。有GAME、REPLAY两种模式。

1、Enum `GroundState`：游戏状态枚举。有READY, RUNNING, PAUSE, OVER四种状态。

1、Class `ImageReader`：静态图像读取器工具类。

1、Class `RePlay`：用于REPLAY的Runnable接口实现。

1、Class `State`：生物体状态枚举。有CHEER, WAIT, ATTACK, FIGHT, DEAD五种状态。

#### Class `Main`
主类。

### 单元测试
#### Package `space`
1、Class `SpaceTest`：测试`bind()`方法、`unbindAll()`方法、`getPos()`方法

#### Package `util`
Class `ArchiveIOTest`：测试`read()`、`write()`方法

Class `FormationReaderTest`：测试`read()`方法

## 自动构建
本项目使用的自动构建工具为 **Maven**，版本 **3.5.2**
- **mvn package**：自动打包项目。其中，`target`目录下的`Assignment03-1.3.jar`是不指定主类版本，`Assignment03-1.3-jar-with-dependencies.jar`是带依赖并且指定主类的版本
- **mvn clean**：自动清理项目。删除`target`目录
- **mvn test**：自动测试。调用项目中的所有单元测试方法进行测试

## 说明
每种阵法**只能由七人**才能施展，多一人不可，少一人亦不行。

## 遇到的问题
1、**问题描述**：在IEDA里运行正常，但是打包为可执行的jar包之后，报各种找不到文件的错误。

**解决方案**：使用 `getResource()` 来读取`URL`而不是直接使用`File`来读取文件或者直接通过`pathname`来读取 `Image`。这里其实还有一个坑，就是`getResource()`中使用的 `path` 的问题:
- `path`不以'/'开头时，默认是从此类所在的包下取资源
- `path`以'/'开头时，则是从`ClassPath`根下获取

2、**问题描述**：在加入背景图的`JPanel`上添加`Button`时，初始时`Button`不显示，当鼠标扫过的时候才显示

**解决方案**：原因应该是`Button`的绘图是在`super.paint(g)`中完成的，而背景的绘图是在当前的派生类中完成的，所以会把`Button`给覆盖掉。通过在`drawImage()`后加上`Button.repaint()`，解决了这个问题。

3、**问题描述**：在运行时，一轮游戏结束之后，想要回放这一轮的存档，结果提示找不到文件，但关闭程序重新运行的时候，就可以回放该轮存档

**解决方案**：原因是存档的时候选择的路径是`src/main/resources`,而运行时读取的文档是从`target/classes`路径下读取的。本次运行的时候该路径下没有存档，自然找不到，而下次打开程序时，Maven会将`src/main/resources`下的存档自动拷贝到`target/classes`路径下，故而可以进行回放。解决的方法是存档时在两个路径下都保存文件，这样无论是本次还是下次运行程序，都可以找到存档。

## **最后的最后**

有一个并发bug实在是调不出来了，明天要考试……最终还是弃了……

但是啊，非常感谢曹老板和余老师这一个学期的辛苦付出，虽然自己以前自学过一点Java，但是听了两位老师的课以后感觉学到了很多很厉害的东西或者称其为思想吧。给两位老师点赞撒花🌺🌺🌺
