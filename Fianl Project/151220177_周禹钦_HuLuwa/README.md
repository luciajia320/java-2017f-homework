

# JAVA Final Project Description

* 主要对象

* 功能描述

* 重要代码

* 遇到的问题

***
## 主要对象
- 葫芦娃
<pre><code>
	class HuLuWa
		private COLOR color;//葫芦娃的颜色
		private SENIORITY seniority;//葫芦娃的排位
		private Position position;//葫芦娃的位置
		private int strength=70;//葫芦娃的战斗力
		private PARTISAN partisan=PARTISAN.善////葫芦娃所属势力
</code></pre>

- 爷爷、蝎子精、蛇精和仆从
<pre><code>
	class T
		private Position position;//位置
    		private int strength=X;//战斗力
    		private PARTISAN partisan=PARTISAN.X;//所属势力
</code></pre>

>这几个对象的成员变量相同、方法相同，不过具体数值不同。

- 阵型(Formation)
<pre><code>
	class Formation
    		private Creature[][] matrix=new Creature[8][8];//存储阵型的具体信息
    		private int kind=X;//阵型的种类
</code></pre>

>阵型设计为8x8的一个矩阵，分为善恶两方，构造函数根据传入的参数，判断应该加载善/恶的阵型。

- 玩家(Player)

<pre><code>
	class Player extends Thing2D implements Runnable
		private int order;//玩家的顺序
    		private boolean alive;//玩家是否还存活
   		private Field field;//玩家所在的场地
    		private Creature role;//玩家角色
    		
    	class Thing2D
    		private final int SPACE = 20;//间距
    		private int x;//物体位置横坐标
    		private int y;//物体位置纵坐标
   		private Image image;//物体的图像
</code></pre>

>玩家共有16个，调用构造函数 Player(int x, int y, Field field,Creature c) 初始化玩家所在位置、所在场地和角色
<pre><code>
	public int distanceSqure(Player a)
</code></pre>
>能得到this与a之间距离的平方
<pre><code>
	private URL getHeadPic()
</code></pre>
>通过玩家角色判断应该载入的图像，返回图像的URL
<pre><code>
	public boolean battle(Player b)
</code></pre>
>判断this玩家与b玩家战斗的结果，玩家获胜的概率为玩家战斗力在双方战斗力之和中所占比重
<pre><code>
	public void move(Player a)
</code></pre>
>移动玩家，玩家向a所在方向走1/16的距离，此过程会向记录文件输出相关移动信息
<pre><code>
	public void move(int x,int y)
</code></pre>
>移动玩家，玩家直接移动到坐标(x,y)处，此过程不会输出记录信息,用于回放系统
<pre><code>
	private Player findClosest()
</code></pre>
>先刷新战场，进行一轮战斗，然后寻找与当前玩家相距最近的活着的敌对玩家，确定行军目标
<pre><code>
	public boolean Alive()
</code></pre>
>判断当前玩家是否还存活
<pre><code>
	public void Dead()
</code></pre>
>杀死当前玩家
<pre><code>
	public void run()
</code></pre>
>战斗、搜索目标、随机暂停


- 场地(Field)

<pre><code>
	class Field
		private final int OFFSET = 20;
    		private final int SPACE = 20;
    		private int w = 0;
    		private int h = 0;
    		private int formA;//善方的阵型类型
    		private int formB;//恶方的阵型类型
    		private int countActionThread;//记录活动的玩家线程个数
    		private int countplayer;//记录玩家个数
    		private int battlex;//战斗计数

    		private boolean finished=true;//战斗是否已经停止
    		private boolean completed = false;
    		private boolean replaymode=false;//是否处于回放的模式

    		private ReportBattle record;//用于输出记录
    		private ReplayBattle bioscope;//用于回放记录

    		private Thread[] threads=new Thread[16];//存储玩家线程
    		private ArrayList tiles = new ArrayList();
    		private Player[] players=new Player[16];//存储玩家
    		private Position[][] positions=new Position[8][16];
    		private Creature[][] creatures=new Creature[8][16];//存储生物信息
</code></pre>
>通过构造函数Field()调用初始化的field的函数

<pre><code>
	public final void initWorld()
	private void getRandomField()
</code></pre>
>initWorld()调用getRandomField()，初始化数据，并随机生成两个阵型，组合成一个Field

<pre><code>
	public final void loadWorld()
	public void getReloadField(int a,int b)
</code></pre>
>loadWorld()调用getReloadField()，初始化数据，并生成a、b对应的两个阵型，组成一个Field


<pre><code>
	public void reloadLevel()
	public void restartLevel()
</code></pre>
>分别用于按下L时，载入Field和按下R时，随机生成Field

<pre><code>
	public void buildWorld(Graphics g)
</code></pre>
>显示生成Field

<pre><code>
	public void freshBattle()
</code></pre>
>刷新Field,进行一波攻击操作

<pre><code>
	public void ReplayOn()
</code></pre>
>开始回放，线程bioscope开始跑

<pre><code>
	public void moveplayer(int p,int x,int y)
	public void killplayer(int i)
</code></pre>
>用于回放时，通知Field，玩家p移动到了(x,y)处；玩家i被杀了

<pre><code>
	public Player findClosest(Player a)
</code></pre>
>找到离玩家a最近的活着的敌对玩家

<pre><code>
	class TAdapter extends KeyAdapter
		public void keyPressed(KeyEvent e)
</code></pre>
>用于响应键盘按键，按下R，刷出新的阵型；按下L，载入战斗记录；按下Space，战斗开始


- 战斗记录(ReportBattle)

<pre><code>
	class ReportBattle implements Serializable
    		ObjectOutputStream outfile;
</code></pre>
>一个使用了Serializable接口的对象，将过程信息序列化输出到文件，通过构造函数ReportBattle()打开目标文件 ，通过endReport()关闭文件。

<pre><code>
	public void reportInit(int i,int j)
</code></pre>
>将初始化Field的两个阵型序号序列化输出

<pre><code>
	public void reportMovement(Player p,int x,int y)
	public void reportKill(int i)
</code></pre>
>当玩家移动时调用第一个函数，输出：0  玩家p的序号  x  y ；意为：移动：玩家p走到位置(x,y)处。当玩家被击杀时调用第二个函数，输出：1  玩家序号 i；意为：击杀：玩家 i被击杀。


- 战斗回放(ReplayBattle)


<pre><code>
	class ReplayBattle implements Serializable,Runnable
    		ObjectInputStream infile;

    		private int nAct;//记录读取的操作序列的个数
    		private int fi=2;//指示当前读取的操作序列位置

    		private Field fieldp;//回放的战场
    		private ArrayList AllAction;//存储所有操作序列
</code></pre>
>通过调用构造函数ReplayBattle(Field d) 弹出选择文件对话框选择文件，并初始化field,读取所有操作到AllAction中，调用endReplay()关闭文件。

<pre><code>
	public void getAllRecord()
</code></pre>
>读取记录文件中所有信息，存储到AllAction中待稍后使用。

<pre><code>
	public boolean isMove(int i)
	public boolean isKill(int i)
</code></pre>
>判断当前操作是否是移动/击杀

<pre><code>
	public void run()
</code></pre>
>线程没有被打断时，分析AllAction中的操作，并通知Field执行，每次执行完一个操作，线程休息2秒并重绘界面

***
## 功能描述
>在一个160X320的场地上，爷爷带着7个葫芦娃以随机阵型站于左侧，蝎子精、蛇精带着6个仆从以随机队形站于右侧，每个生物单独实现为一个线程。按下Space，战斗开始，各生物向最近的敌人行进并攻击，攻击范围为50个像素点，获胜方根据双方战斗力占总战斗力比重决定。当一方生物全部阵亡时，战斗结束，这个过程会被记录到文件。按下R可以重新生成随机阵型。按下L键，弹出文件选择对话框，选择某个记录文件即可载入，再按下Space即开始回放。
>
>因为Field随机生成，ReportBattle和ReplayBattle均依赖于这个随机的Field，它们的单元测试并不好写（不太会写），所以我选择对Player类中的Move()函数进行单元测试，测试内容：存活状态下的行进；跨度过大的行进不执行（疑似某个小bug）；杀死玩家；死亡玩家的移动不执行。


***
## 重要代码

- ReplayBattle
<pre><code>
    	public void run() {
        	while (!Thread.interrupted()) {
            		if (this.isMove(fi)) {//当前操作是否是移动
               	int p = (int)AllAction.get(fi + 1);
                	int x = (int)AllAction.get(fi + 2);
                	int y = (int)AllAction.get(fi + 3);
                	System.out.println(p + "->" + x + "," + y);
                	fieldp.moveplayer(p,x,y);//移动玩家
                	fi += 4;移到下一个操作位置
                	fieldp.repaint();//重绘
            	}
            	else if (this.isKill(fi)) {//当前操作是否是击杀
                	int k = (int)AllAction.get(fi + 1);
                	fieldp.killplayer(k);//杀死玩家
                	System.out.println("player " + k + " dead");
                	fi += 2;//移到下一个操作位置
                	fieldp.repaint();、、重绘
            	}
            	else Thread.interrupted();
            		try {
                		Thread.sleep(500);//暂停半秒
           		}
            		catch (Exception e) {
            		}
        	}

    	}

</code></pre>

- Field

<pre><code>
	public void freshBattle(){
        	boolean noneenemy=true;//指示是否还有敌人
       		for(int i=0;i<16;i++)
            		for(int j=i+1;j<16;j++){
                		if(players[i].playerPartisan() ==players[j].playerPartisan())
                    		continue;//同伙忽略
                		else if(!players[i].Alive())
                    		continue;//敌方已死亡，忽略
                		else if(!players[j].Alive())
                    		continue;//自己已死亡，忽略
                		else {
                    		noneenemy=false;
                   			if(players[i].distanceSqure(players[j])<2500) {//在攻击范围内
                        			if (players[i].battle(players[j])) {//i战胜j
                            				record.reportKill(j);//记录击杀
                            				threads[j].interrupt();//j线程中断
                            				players[j].Dead();//击杀j
                            				players[j].setImage(null);
                        			}//j lose
                        			else {//j战胜i
                            				record.reportKill(i);//记录击杀
                            				threads[i].interrupt();//i线程中断
                            				players[i].Dead();//击杀i
                            				players[i].setImage(null);
                        			}//i lose
                        		}
                    		else continue;
                    	}
                	}
        	if(noneenemy){//没有敌人了就中断所有活着的玩家线程
            		for(int i=0;i<16;i++)
                		if(players[i].Alive())
                    		threads[i].interrupt();
        	}
      	}

</code></pre>


- Player
<pre><code>
	public void run() {
        	while (!Thread.interrupted()) {
            	Random rand = new Random();

            	Player temp=findClosest();//寻找最近的敌方玩家，寻找之前已进行过攻击操作
            	if(!Alive())//若自己被击杀，则直接跳出循环
                	continue;
            	this.move(temp);//移动玩家
            	try {

                	Thread.sleep(rand.nextInt(1000) + 1000);//随机暂停
                	this.field.repaint();//重绘

            	} 
            	catch (Exception e) {
            	}
        	}
    	}

</code></pre>

***
## 遇到的问题
- 回放系统
 >原本我没有打算用线程实现回放系统，只用了一个循环分析操作序列，然后通知Field执行操作，然而，为了实现刷新画面后暂停2秒的功能，我在其中加入Thread.Sleep(2000)，画面却意外卡住，根据控制台输出的信息可以确定程序在跑，然后图形界面却卡住、未响应。后来尝试过Timer()和时间戳来实现暂停2秒，均是相同情况，只好改用线程实现，却意外成功，查阅过网上资料，没有相关解答，至今也不知道为什么。
   
- 同归于尽
>对于战斗a和b的玩家1和玩家2，若在(0,a+b)的范围内取到的随机数小于a，则玩家1获胜，否则玩家2获胜。我在正式的“杀死”一个玩家之前会判断，执行击杀操作的玩家a是否已经死了，若死亡则不执行，但因为玩家是多线程的，会遇到这样的情况：玩家a判断与玩家b战斗获胜，准备击杀但还未执行击杀操作；玩家b判断与玩家a战斗也获胜，此时玩家b判断自己是存活的，即将a击杀，然后a会在击杀b之后结束线程，这时候显示为玩家a、b同归于尽。对于这种情况其实只要再额外定义一个准备击杀的队列，在准备击杀前，访问队列：若有人先准备击杀自己，则放弃击杀，否则，则进入准备击杀的队列；但是这种情况有其现实存在的合理性，比如临死前的舍身一击，因此没有将其消去，所以战斗到最后可能会出现平局的情况。

***
															151220177 周禹钦