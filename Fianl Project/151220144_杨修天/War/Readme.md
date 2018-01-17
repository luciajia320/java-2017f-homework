# **葫芦娃大作业**

## 战斗过程概述
首先，main函数会初始化一个战场

正反两方在战场两边列队

按下空格，开始战斗

回合一：

双方互相发起冲锋，横跨整个站场，互有伤亡（小概率一方团灭）

双方交手过后，进入回合二：

存活的葫芦娃合二为一（站在间隔的位置上，从图片上来看像是重合的），来到战场中心

怪物一方也想战场中心移动，进行最后的火并，直至一方团灭

幸存者继续移动回原位，战斗结束

## 生物体对象：

++蛇精
爷爷
蝎子精
小怪
葫芦娃++

（以上对象都是 implements 一个 interface类creature）

public interface creature {
	。。。
}

生物题内部结构并不复杂，大体类似，但是分为正反两派，后面有说明此举原因

以下用蛇精举例（==并非完整代码，只为解释说明==）

    public class Snake implements Creature,Runnable{
	private WarGround s;//所在战场
	private int live;//是否存活
	private ImageIcon image;  //人物图片
	private int x;
	private int y;//坐标
	
	public void moveto(int a,int b)
	{	
	    //回合二的移动操作，遇敌则战斗
	}
	public void die()
	{      //战斗失败，死亡
		this.live=0;
		image=new ImageIcon(this.getClass().getResource("dead.png"));
		s.shejingdie(x,y);
	}
	public void run()//各生物均有自己的线程
	   {	//循环条件是：自己存活并且地方有人存活
		 while(this.live==1&&(s.getholder().getzheng()>0))
		 { 	  try { 
			   //处于回合一
			 if(s.getround()==1)
			 {//前方无人，移动
		 	   if(x>0&&!(s.getholder().haspeople(x-1,y)))
		 	   {
		 		  x-=1;
			   s.shejingmove(x+1,y,x,y);
			   
		 	   }
		 	   //前方有人，战斗
		 	  else if(x>0)
		 	   {   Creature l=s.getholder().getliver(x-1, y);
		 		   if(l instanceof Huluwa)
		 		   {int index=(int)(Math.random()*10);  
		 		   	if(index<3)
		 		   	{  this.live=0;
		 			  image=new ImageIcon(this.getClass().getResource("dead.png"));
		 			 s.shejingdie(x,y);}
		 		   	else
		 			   ((Huluwa) l).die();
		 		   }
		 		   else if(l instanceof Grandpa)
		 		   {
		 			  ((Grandpa) l).die();
		 		   }
		 	   }
		 	   //横越战场，进入回合二
		 	  else if(x==0)
		 		  s.setround();
			 }
			 //回合二进入战场中心决斗
			 else
			 {
				 moveto(375,225);
			 }
		 	   Thread.sleep(3);  }
			   catch (Exception e) {  
			         // TODO Auto-generated catch block  
			         e.printStackTrace();  
			     }   
		 }
		 //战斗结束，胜利者回到本方阵营
		 while(s.getholder().getzheng()==0)
		 {	try { moveto(740,150);
			Thread.sleep(5);  }
		   catch (Exception e) {  
		         // TODO Auto-generated catch block  
		         e.printStackTrace();  
		     }  
		}
		}
}




## 非生物对象：

Holder：
存放生物体位置和生物体，负责移动，移除，定位等

    public class Holder {
	private int heng;
	private int zong;
	private int field[][];
	private Creature liver[][];
	private int zheng;//正方存活人数
	private int fan;//反方存活人数
        ...
    }

**==战场==：存放所有对象**

    public class WarGround extends JFrame implements KeyListener{
	private int round;//回合数
	private Holder holder;//holder存放生物体对象，记录位置
    private JLabel back; //背景
    private JLabel [] bro;//葫芦娃
    private Huluwa brothers[];
    private ImageIcon[]images;
    private Grandpa yeye;
    private JLabel grand;//爷爷
    private ImageIcon grandpa;
    private ImageIcon she;
    private Snake beauty;
    private JLabel snake;//蛇
    private Xiezijing guai;
    private JLabel xiezi;//蝎子精
    private ImageIcon xie;
    private Luoluo []xiaode;
    private JLabel []louluo;//喽啰
    private ImageIcon luo;
    public WarGround(){  
    	round=1;
    	holder=new Holder();
    	this.setResizable(false);//不能够修改大小  
        this.getContentPane().setLayout(null);  
        this.setTitle("葫芦娃战争");  
        this.setBounds(300,100,800,500);  
        back=new JLabel();  
        ImageIcon icon=new ImageIcon(this.getClass().getResource("back.gif"));  
        back.setIcon(icon);  
        back.setBounds(0,-20,800,500);  
        this.getContentPane().add(back);
        addKeyListener(this);
        brothers=new Huluwa[7];
        xiaode=new Luoluo[7];
        //初始化葫芦娃和怪，设置图像和位置
		 for (int i = 0; i < brothers.length; i++)
		 {
	            brothers[i] = new Huluwa(Identity.values()[i],this);
	            xiaode[i]=new Luoluo(i,this);
	     }
		 bro=new JLabel[7];
		 images=new ImageIcon[7];
		 louluo=new JLabel[7];
		 luo=new ImageIcon();
		 for(int i=0;i<7;i++){  
	            bro[i]=new JLabel();  
	            images[i]=brothers[i].getimage();   			
	            bro[i].setSize( images[i].getIconWidth(), images[i].getIconHeight());  
	       bro[i].setIcon(images[i]);
	       this.back.add(bro[i]);
	       louluo[i]=new JLabel(); 
	       luo=xiaode[i].getimage();
	       louluo[i].setSize( luo.getIconWidth(), luo.getIconHeight());  
	       louluo[i].setIcon(luo);
	       this.back.add(louluo[i]);
		 }    
		 	for(int i=0,j=1,k=1;i<7;i++,k++) 
		 	{
		 		bro[i].setLocation(j*100,i*50+100); 
		 		brothers[i].setposition(j*100,i*50+100);
		 		holder.setliver(j*100, i*50+100, brothers[i]);
		 		
		 		louluo[i].setLocation(j*500+k*30,i*50+100); 
		 		xiaode[i].setposition(j*500+k*30,i*50+100);
		 		holder.setliver(j*500+k*30,i*50+100,xiaode[i]);
		 	}
		 	//初始化爷爷，设置图像和位置
		 	yeye=new Grandpa(this);
		 	grand=new JLabel();
			grandpa=new ImageIcon();
			grandpa=yeye.getimage();
			grand.setSize( grandpa.getIconWidth(), grandpa.getIconHeight());
			grand.setIcon(grandpa);
		    this.back.add(grand);
		    grand.setLocation(50,200); 
		    yeye.setposition(50,200);
		    holder.setliver(50,200,yeye);
		    //初始化蛇精，设置图像和位置
		    beauty=new Snake(this);
		 	snake=new JLabel();
			she=new ImageIcon();
			she=beauty.getimage();
			snake.setSize( she.getIconWidth(), she.getIconHeight()); 
			snake.setIcon(she);
		    this.back.add(snake);
		    snake.setLocation(700,250); 
		    beauty.setposition(700,250);
		    holder.setliver(700,250,beauty);
		   //初始化蝎子精，设置图像和位置
		    guai=new Xiezijing(this);
		 	xiezi=new JLabel();
			xie=new ImageIcon();
			xie=guai.getimage();
			xiezi.setSize( xie.getIconWidth(), xie.getIconHeight()); 
			xiezi.setIcon(xie);
		    this.back.add(xiezi);
		    xiezi.setLocation(500,200);
		    guai.setposition(500,200);
		    holder.setliver(500,200,guai);
		    
		    this.setVisible(true); //设置可见
		    
		    
		    
    }  
    //各个生物的各种移动操作
    public void yeyemove(int xx,int yy,int x,int y)
    {
    	holder.moveliver(xx,yy);
    	holder.setliver(x,y,yeye);
    	grand.setLocation(x,y); 
    }
    public void huluwamove(int xx,int yy,int x,int y,int z)
    {	holder.moveliver(xx,yy);
    	holder.setliver(x, y,brothers[z]);
    	bro[z].setLocation(x,y); 
    }
    public void shejingmove(int xx,int yy,int x,int y)
    {	holder.moveliver(xx,yy);
		holder.setliver(x,y,beauty);
    	snake.setLocation(x,y); 
    }
    public void luoluomove(int xx,int yy,int x,int y,int z)
    {	holder.moveliver(xx,yy);
    	holder.setliver(x,y,xiaode[z]);
    	louluo[z].setLocation(x,y); 
    }    
    public void xiezijingmove(int xx,int yy,int x,int y)
    {	holder.moveliver(xx,yy);
		holder.setliver(x,y,guai);
    	xiezi.setLocation(x,y); 
    }
    //各种生物的死亡操作
    public void luoluodie(int x,int y,int z)
    {	holder.fandie(x, y);
    	ImageIcon temp=new ImageIcon();
    	temp=xiaode[z].getimage();
    	louluo[z].setSize(temp.getIconWidth(),temp.getIconHeight()); 
		louluo[z].setIcon(temp);
    	louluo[z].setLocation(x,y); 
    }   
    public void xiezijingdie(int x,int y)
    {	holder.fandie(x, y);
    	ImageIcon temp=new ImageIcon();
    	temp=guai.getimage();
    	xiezi.setSize(temp.getIconWidth(),temp.getIconHeight()); 
		xiezi.setIcon(temp);
    	xiezi.setLocation(x,y); 
    }  
    public void shejingdie(int x,int y)
    {	holder.fandie(x, y);
    	ImageIcon temp=new ImageIcon();
    	temp=beauty.getimage();
    	snake.setSize(temp.getIconWidth(),temp.getIconHeight()); 
    	snake.setIcon(temp);
    	snake.setLocation(x,y); 
    }  
    public void huluwadie(int x,int y,int z)
    {	holder.zhengdie(x, y);
    	ImageIcon temp=new ImageIcon();
    	temp=brothers[z].getimage();
    	bro[z].setSize(temp.getIconWidth(),temp.getIconHeight()); 
		bro[z].setIcon(temp);
    	bro[z].setLocation(x,y); 
    }  
    public void yeyedie(int x,int y)
    {	holder.zhengdie(x, y);
    	ImageIcon temp=new ImageIcon();
    	temp=yeye.getimage();
    	grand.setSize(temp.getIconWidth(),temp.getIconHeight()); 
		grand.setIcon(temp);
    	grand.setLocation(x,y); 
    }  
    public Holder getholder()
    {
    	return holder;
    }
    public int getround()
    {
    	return round;
    }
    public void setround()
    {
    	round++;
    }
    //接受键盘信息，按空格键战斗开始
    public void keyPressed(KeyEvent e)
	{	
	if(e.getKeyChar()==e.VK_SPACE)
		{	//创建线程池，将所有生物体线程放入
		ExecutorService exec = Executors.newFixedThreadPool(100);
	        exec.execute(yeye);
	        exec.execute(beauty);
	        for(int i=0;i<7;i++)
	    	{exec.execute(brothers[i]);
	         exec.execute(xiaode[i]);
	    	}
	    exec.execute(guai);
		}
		
	}
    public void keyTyped(KeyEvent e)
	{	
	}
    public void keyReleased(KeyEvent e)
	{	
		
	}
    
}

