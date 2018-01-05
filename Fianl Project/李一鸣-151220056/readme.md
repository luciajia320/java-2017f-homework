葫芦娃大战妖精
===
前言故事
---
    七个葫芦娃和爷爷遭遇到蛇精、蝎子精带领的六个喽啰一起,葫芦娃摆出长蛇阵保护爷爷，妖精们站出鹤翼形阵法，蝎子精冲锋，蛇精殿后，战争一触即发<br>
战争场面
---
####开始界面<br>
![Image text](https://github.com/YMing-Li/java-2017f-homework/blob/master/Fianl%20Project/%E6%9D%8E%E4%B8%80%E9%B8%A3-151220056/relatedpicsfor_readme/start.png)  
####结束界面<br>
####葫芦娃赢<br>
![Image text](https://github.com/YMing-Li/java-2017f-homework/blob/master/Fianl%20Project/%E6%9D%8E%E4%B8%80%E9%B8%A3-151220056/relatedpicsfor_readme/end_win.png)  
####葫芦娃输<br>
![Image text](https://github.com/YMing-Li/java-2017f-homework/blob/master/Fianl%20Project/%E6%9D%8E%E4%B8%80%E9%B8%A3-151220056/relatedpicsfor_readme/end_lose.png) <br>
相关规则
---
#####结果判定:爷爷阵亡，葫芦娃输；妖精全部阵亡，葫芦娃赢<br>
#####移动速度:爷爷速度为1，葫芦娃、妖精速度为1~2<br>
#####下一位置周围遭遇判定:葫芦娃遭遇妖精2/3概率杀死；妖精遭遇葫芦1/3概率杀死，遭遇爷爷1/15杀死<br>
#####移动策略:优先向战场中部移动<br>
代码框架<bc>
---
####Creature生物体的抽象：葫芦娃、爷爷、妖精（喽啰、蝎子精、蛇精）、空,是RUNNABLE实现，包含生物体的相关操作和run<br>
####Position位置的抽象，包含位置的信息和相关操作<br>
####Formation阵法接口，具体实现为Snake、CraneWing、Audience<br>
####Queue队伍的抽象，分为葫芦娃队，妖精队，观众队（葫芦娃三）<br>
####Field战场抽象，包含position、creature两个二维数组，主要功能战场初始化，GUI显示，多线程启动<br>
####Main主函数，先生成葫芦娃队摆阵形，妖精队摆阵形，观众队摆阵形，接着均放入战场，然后战场GUI化<br>
操作方法<br>
---
  空格键开始（生物体开始运动战场记录，死亡体留在原地，其他继续，结果可判定后所有生物体停下，显示结果）<br>
未完成功能
---
  L键回放.未完成原因：生物体A从位置1移动到位置2，位置2上可存入A生物体，相同原理代码下，位置1难以存入Blank生物体。此bug导致移动轨迹留下，记录out.txt成功复盘失败<br> 
结束感言
---
  拖延症必须得戒！刚开始写大作业有不听老人（曹老板）言，吃亏在眼前的后悔，但等到最终实现了（bug真的找好久，心累）残缺的“小狗”，心里还是很开心的<br>
