关于本次实验（很抱歉非常迟交）<br>
=================
内容
-------
Position类变成了二维表式，含空间横纵坐标x,y,并新增了函数表明其上承载的实体离去。<br>
Creature本来使用的是接口，但由于爷爷（Grandpa）、蛇精（Snake）等生物均存在相同的名字属性、需要获得其站位和更改其站位的功能，因此变成抽象类，具体的实现留到继承的派生类来做。<br>
葫芦娃Huluwa除name属性外还有Color属性和Seniorty属性，重写了getName方法返回颜色的toString函数。<br>
Granpa类、Snake类、Scorpion类均有特殊的功能：爷爷浇灌了葫芦兄弟的成长成熟、蛇精收留了蝎子精小弟，因此获得蝎子精带领的众喽啰，以上过程均表现为GiveBirth()方法。<br>
Camp表示阵营的基类，一个阵营有唯一的leader、数个成员members、和阵型（formation）,可以往阵营中添加新成员，创建第一个阵型，对于正义联盟JusticeCamp，有对成员进行排序的一个Sorter，和打乱成员的方法（collection.shuffle），对于邪恶势力，他们可以变换阵型（ChangeFormation）。<br>
上帝God拥有一片M^2的土地，和两个它创建的阵营（justicecamp和evilcamp），自上帝出现便创立了两个阵营，上帝管辖并具体赋予两个阵营内容（使Leader诞生并让leader生出更多成员），给他们的阵型指定在土地中的起点，指定leader的站位等等。但是邪恶势力的换阵营工作具体的就由他们自己来操控了（需要从土地上把之前的阵营和leader的存在移除并layout新的站位）。在往土地上加入新的阵型要注意是否会覆盖或被已有阵型包含。<br>
```
ground.layoutLeader(snake,ground.getPosition(1,9));//邪恶势力领导上位
ground.layoutFormation(evilCamp.formation, ground.getPosition(3, 3)); //放置阵型
```
上帝操纵的内容可以用如下代码来表示：<br>
```
God god=new God();
god.tellStory("上帝创建了两个阵营，一是正义的阵营，二是邪恶的势力\n正义联盟由爷爷和他种下成精的葫芦娃带领\n邪恶势力由蛇精以及她的小弟蝎子精带领的下喽啰队构成");
god.createCamp();
god.createJusticeCamp();
god.createEvilCamp();

god.tellStory("有一天，邪恶势力在领导蛇精的带领下挑衅了措手不及的爷爷一家人，当前局面如下");
god.printBattle();

god.tellStory("正义联盟的爷爷和葫芦兄弟马上调整了队形应对挑衅，当前局面如下");
god.sortJusticeCamp(); //排序后重新输出
god.printBattle();

god.tellStory("邪恶势力见状更为挑衅了，又变换了新的队形，当前局面如下");
god.changeEvilCamp();
god.printBattle();

god.tellStory("这场战役即将打响，终究鹿死谁手，敬请期待下期节目......");
```
使用方法
---------
实现了基本要求，使用了接口、抽象类、聚集、继承等多种模式。<br>
运用了异常处理的技巧在超过限制时告知使用者时：<br>
```
try { //放置阵营可能超出边界或与已存在阵型相互影响
        ground.layoutLeader(snake,ground.getPosition(1,9));
        ground.layoutFormation(evilCamp.formation, ground.getPosition(3, 3)); //放置阵型
    }catch (LayOutException e){
        System.out.println(e.getMessage());
        e.printStackTrace();
    }
    evilCamp.setMembersPosition(ground);//确定阵型后需要把阵型上应占用的位置落实给每个成员
```
优点
-----------
使用派生类和接口简化了代码管理，并且对于续写空间较大。<br>
由上帝统一管理全员的诞生，符合工厂模式的要求，每个类的指责不说单一但是也划分清楚明确容易指派。<br>
利用集合框架易于给多变的数组添加成员和除去成员。<br>
把各个实体记录到土地上，且只记录leader和队形，易于管理并由土地自带的方法来将位置初始化并赋予每一个生物所在的位置。<br>
各阵型只存储一个字符串二维数组而不是存储具体的位置，便于修改。<br>
