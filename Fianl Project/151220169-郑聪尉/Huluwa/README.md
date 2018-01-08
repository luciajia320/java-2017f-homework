# Huluwa

**郑聪尉 151220169@smail.nju.edu.cn**

## 开发环境

* IntelliJ IDEA 2017.2；
* Java8；

## 项目预览

Huluwa 17f-Final 版本是葫芦娃大战的自动化程序，支持存档读档并可回放精彩过程；

![Huluwa0](https://github.com/challvy/java-2017f-homework/raw/master/Fianl%20Project/151220169-郑聪尉/Huluwa/prtSc/Huluwa0.png)

**可用Maven打包后运行Huluwa.jar，按空格键即可立即开始多线程战斗，更多界面截图可在网页末尾查看**

## 项目详情

### 目录
* /document: 存档与读档文件夹
* /javadoc: javadoc文件夹
* /src: 工程文件夹
* /target: 构建目标文件夹

### 状态机

![Huluwa-Status](https://github.com/challvy/java-2017f-homework/raw/master/Fianl%20Project/151220169-郑聪尉/Huluwa/prtSc/Huluwa-Status.png)

### 框架

* Main类: 程序的入口；
* iofile包: 文件操作；
* object包: 二维对象以及各类角色定义；
* storyboard包: 运行逻辑与界面；

## 设计要点

### 封装与继承

* Object类表示二维对象，可派生出Creature抽象类，也可直接用来定义背景、图标等；
* Creature抽象类表示各色角色，派生出士兵和平民两项抽象类，由其生命特特征，需实现Runnable接口来进行线程编程；
* Creature抽象类有前后遮挡关系，需实现Comparable接口来进行空间排序；
* Soldier抽象类有进攻属性和方法，故需要添加进攻起始时间戳和终止时间戳来控制进攻时长；

![Huluwa-UML](https://github.com/challvy/java-2017f-homework/raw/master/Fianl%20Project/151220169-郑聪尉/Huluwa/prtSc/Huluwa-UML.png)

### 集合与泛型

程序用到Vector<>来存储各类角色，如Vector<Creature>存放roles和death，两个容器实时增加或删减，程序效率会更高。

### 多态

Battle类中的roles和death使用泛型Vector<Creature>来存放不同角色，其中Creature为抽象基类，体现了多态；

<pre><code>private void initRole(){
        roles = new Vector<Creature>();
        for(Huluwa h: huluwa) {
            roles.add(h);
        }
        roles.add(yeye);
        roles.add(snake);
        roles.add(scorpion);
        for(DemonTroop d: demonTroop){
            roles.add(d);
        }
    }
</code></pre>

### 设计原则

#### 一、单一职责原则
> IOFile类仅实现文件读写；Creature仅实现角色属性与方法；

#### 二、里氏替换原则
> 所有的Creatures都能被Huluwa, Yeye, Snake, Scorpion或者DemonTroop替换；

### 注解

文档使用@author和@see注解，其方法使用@param和@return注解，用来编写javadoc，详情请见javadoc文件夹；

### 输入输出

IOFile类实现：FileReader与BufferedReader用来读取文件，FileWriter与BufferedWriter用来写入文件；

### 线程安全

每个将角色类中都存有Battle类用来与同一个逻辑控制后台交互，故需添加synchronized和volatile关键字；

### 单元测试

角色类以葫芦娃为代表进行测试即可。

* testPosition()用来测试位置信息，被测试的方法有: x(), y()；
* testLive()用来测试生命属性，被测试的方法有: isLive()；
* testRole()用来测试正义或邪恶，被测试的方法有: isRightRole()；
* testImage()用来测试图片属性，被测试的方法有: getImage()；
* testAttack()用来测试攻击属性，被测试的方法有: isAttacking()；

## 总结

* 设计前期经老师提醒，对抽象封装有了更深的体会；
* 中期根据教学进度，陆续添加功能，缝缝补补；
* 后期在实现界面与复盘功能的时候，在原来码得自由自在的情况下，遇到不小的冲击，需要对框架进行比较大幅度改变；

> 附录

**葫芦娃正大杀四方，打得酣畅淋漓收服了俩妖精，可是小喽啰却发现了葫芦娃简单AI的破绽，直向爷爷奔去！预知后文如何，可查看葫芦娃篇章之功亏一篑！**
![Huluwa1](https://github.com/challvy/java-2017f-homework/raw/master/Fianl%20Project/151220169-郑聪尉/Huluwa/prtSc/Huluwa1.png)

![Huluwa2](https://github.com/challvy/java-2017f-homework/raw/master/Fianl%20Project/151220169-郑聪尉/Huluwa/prtSc/Huluwa2.png)

![Huluwa-BadEnding](https://github.com/challvy/java-2017f-homework/raw/master/Fianl%20Project/151220169-郑聪尉/Huluwa/prtSc/Huluwa-BadEnding.png)

**还好还好，别忘了这是谁的地盘**

![Huluwa](https://github.com/challvy/java-2017f-homework/raw/master/Fianl%20Project/151220169-郑聪尉/Huluwa/prtSc/Huluwa-HappyEnding.png)
