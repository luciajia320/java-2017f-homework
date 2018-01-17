package nju.hulu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Field extends JPanel {
	//场地设置
    private final int OFFSET = 0;
    private final int SPACE = 90;
    private int cw=10;
    private int ch=7;//地砖数量
    private int w = (cw)*SPACE;
    private int h = (ch)*SPACE;// 场地大小
    Image background;
    Image winner;
    //必要图片
    
    //位置和生物
    private ArrayList<Position> pos =new ArrayList<Position>();
    private int servantsNum=8;
    ArrayList<Huluwa> brothers=new ArrayList<Huluwa>();
    ArrayList<Servant> servants=new ArrayList<Servant>();
    private Queue<Huluwa> huluQueue;
	private Queue<Servant> scorpionQueue;
	Snake snake;
	Grandpa grandpa;
	Scorpion scorpion;
	
	
    //状态变量
    private boolean previousfight=false;//回顾模式
    private boolean started=false;//战斗已经开始
    private boolean completed = false;//战斗结束
   
    
    //工作人员
    private StateChecker checker;//
    private Painter painter;//
	private Recorder recorder;
	
	
    public Field() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        initWorld();
    }
    public void startFight()
    {
    		recorder=new Recorder();
     	started=true;
		new Thread(checker).start();
		for (int i=0;i<brothers.size();i++)
		{
			new Thread((BasicHuman)brothers.get(i)).start();
		}
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i=0;i<servants.size();i++)
		{      		
			new Thread((BasicHuman)servants.get(i)).start();
		}
		
		new Thread((BasicHuman)grandpa).start();
		new Thread((BasicHuman)scorpion).start();
		new Thread((BasicHuman)snake).start();
		new Thread(painter).start();
    }
  
	
    public final void initWorld() {

    	   	URL loc2 = this.getClass().getClassLoader().getResource("back.jpg");
   	    ImageIcon iib = new ImageIcon(loc2);
   	    background = iib.getImage();
   	    
    		previousfight=false;
        started=false;
        completed=false;//初始化战场状态
        
        checker=new StateChecker();//
       	painter=new Painter();//
    		recorder=new Recorder();//工作人员就位
       
        for (int i=0;i<cw;i++)
        	for (int j=0;j<ch;j++)
        	{
        		pos.add(new Position<Creature>(i,j));//布置场地
        	}
        
      
        int count=ch*2;   		
		for (int i=0;i<7;i++)
		{
			brothers.add(new Huluwa(COLOR.values()[i],NUMBER.values()[i],pos.get(count),this));			
			count++;
		}
		count=ch*7;//隔开一定距离放置蝎子精一方
		for (int i=0;i<servantsNum;i++)
		{
			servants.add(new Servant(pos.get(count),this));
			count++;
		} 
		
		initQueue(brothers,servants);//两方排成队列
		
		positionSetter fengShiSetter=new FengShi();
		fengShiSetter.setPosition(this.scorpionQueue,pos,cw,ch,SPACE);//蝎子精一方摆出阵列
	
		
		positionSetter ChongESetter=new ChongE();
		ChongESetter.setPosition(this.huluQueue,pos,cw,ch,SPACE);//葫芦娃一方摆出阵列
		
		snake=new Snake(pos.get(trans(cw-5,2)),this);
		grandpa=new Grandpa(pos.get(trans(3,6)),this);
		scorpion=new Scorpion(pos.get(trans(cw-5,ch-3)),this);//蛇精蝎子精老爷爷走到各自到位置
		
		
   
        
    }
    public void initQueue(ArrayList<Huluwa> groupA,ArrayList<Servant> groupB)
  	{
  		huluQueue=new Queue<Huluwa>(groupA);
  		scorpionQueue=new Queue<Servant>(groupB);		
  		huluQueue.setFirst(pos.get(trans(0,0)));
  		scorpionQueue.setFirst(pos.get(trans(cw-5,1)));
  			
  	}

    public synchronized void drawWorld(Graphics g) {
    	
        g.setColor(new Color(250, 250, 250));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());   
	    g.drawImage(background,0,0,this);
	    
        recorder.addList();       
        for (int i = 0; i < pos.size(); i++) {

        	Position temp=pos.get(i);
   
       
        if(temp.exist)
        {   Creature holder;
        		holder=temp.getHolder();
        		if(holder!=null)
        		{
        		g.drawImage(holder.getImage(), temp.getX()*SPACE+OFFSET, temp.getY()*SPACE+OFFSET, this);       
           	recorder.addNode(temp.getX()*SPACE+OFFSET, temp.getY()*SPACE+OFFSET,holder.getImgURL());
        		}
           	
        }
       
         

        }
        if(completed)
		{       		
    	   		   		
       	    g.drawImage(winner, 0,300,this);
			
		}
        		
            if (!started) {
             	URL loc3 = this.getClass().getClassLoader().getResource("start.png");
           	    ImageIcon iia = new ImageIcon(loc3);
           	    Image start = iia.getImage();
           	    g.drawImage(start, 0,300,this);
                
            }
       
    } 
    
//绘制读取内容
 public synchronized void drawHistory(Graphics g) {
	    g.setColor(new Color(250, 250, 250));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.drawImage(background,0,0,this);
     /*   for (int i = 0; i < pos.size(); i++) {
        	Position temp=pos.get(i);
      }*/
          
        		RecordList list=recorder.getNext();
        		int t=0;
        		while(list.withinRange())
        		{
        			RecordNode node=list.getNext();        		
        			g.drawImage(node.getImage(),node.getX(), node.getY(), this);
        			
        		}
        		if (recorder.finished())
        		{
        				URL loc = this.getClass().getClassLoader().getResource("finishR.png");
               	    ImageIcon ia = new ImageIcon(loc);
               	    Image finishR = ia.getImage();
               	    g.drawImage(finishR, 0,300,this);
               	    
        			
        		}
        }
 
	
 
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (!previousfight)
        		drawWorld(g);
        else
        		drawHistory(g);
    }

    class TAdapter extends KeyAdapter {
 		
    	
        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();    

            if (key == KeyEvent.VK_SPACE) {
            	//开始游戏
            		if(!started)
            		{
            			restartLevel();
            			startFight();
            		}
            } else if (key == KeyEvent.VK_R) {
            	if (!previousfight&&!completed)
            	{
            		FileHelper helper=new FileHelper();
            		helper.saveRecords(recorder,"unfinished");
            	}
                restartLevel();
                repaint();
            } else if (key == KeyEvent.VK_L) {
            		if(started==false)
            		{
            			try {         		            				
							FileHelper fileHelper=new FileHelper();
							recorder=fileHelper.readFile();
							if (recorder!=null)
							{
								completed=false;
								previousfight=true;
								started=true;
								new Thread(painter).start();
								
							}
							
														
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}          
            		}
            }

          
        }
    }


    public synchronized void endThreads()
    {  		
    	for (int i=0;i<servants.size();i++)
		{
	
			servants.get(i).setState(State.END);
		}
		for (int i=0;i<brothers.size();i++)
		{
			brothers.get(i).setState(State.END);
		}
		grandpa.setState(State.END);
		scorpion.setState(State.END);
		snake.setState(State.END);
		
		
    }
    
    public void restartLevel() {
   
    		
    		completed=true;
    		endThreads();
    		pos.clear();
    		brothers.clear();
    		servants.clear();
    		
    		
        initWorld();
        if (completed) {
            completed = false;
        }
    }
  
    private int trans(int x,int y)//二维下标转换为一维
    {
    		return x*ch+y;
    }
    //
    public ArrayList<Position> getPositions()
   	{
   		return pos;
   	}
   	

    public int getBoardWidth() {
        return this.w;
    }

    public int getBoardHeight() {
        return this.h;
    }
    public int getCw()
    {
    	return cw;
    }
    public int getCh()
    {
    	return ch;
    }

    public boolean isCompleted()
	{
		return completed;
	}
	public boolean isStarted()
	{
		return started;
	}
    private boolean SSend()//蝎子精一方阵亡
    {
    		int i;
		for (i=0;i<servants.size();i++)
			if (servants.get(i).getState()!=State.DEAD)
				return false;
		
		if (scorpion.getState()!=State.DEAD||snake.getState()!=State.DEAD)
			return false;
		return true;
		
    }
    
    private boolean Huluend()//葫芦娃一方阵亡
    {
    	int i=0;
    	for (;i<brothers.size();i++)
    		if (brothers.get(i).getState()!=State.DEAD)
    			return false;
    	if (grandpa.getState()!=State.DEAD)return false;
    	return true;
    }
    
    
    private class StateChecker implements Runnable//检查是否结束战斗的检查员
    {

		public void run() {
			while (!previousfight&&!completed&&started&&!Thread.interrupted()) 
			{			
				if (Huluend())
				{
					FileHelper helper=new FileHelper();
            			
					URL loc = this.getClass().getClassLoader().getResource("snakeWin.png");
		       	    ImageIcon ia = new ImageIcon(loc);
		       	    winner = ia.getImage();
		       	 		
					repaint();	
					helper.saveRecords(recorder,"SnakeWin");
					completed=true;
					repaint();
					endThreads();
					
				}
				else if(SSend())
				{
					FileHelper helper=new FileHelper();
        			
					URL loc = this.getClass().getClassLoader().getResource("huluwaWin.png");
		       	    ImageIcon ia = new ImageIcon(loc);
		       	    winner = ia.getImage();
								
					repaint();	
					helper.saveRecords(recorder,"HuluwaWin");
					
					completed=true;
					repaint();
					endThreads();
					
				}
				
		}
		}
    	
    }
    
    
    private class Painter implements Runnable//更新战况
    {

		public void run() {
			
			while (!completed&&started&&!Thread.interrupted()) 
			{				
					repaint();
				
				 try {		      	           
		            	Thread.sleep(200);
		             
		             } catch (Exception e) {
		            	 
		             }					
			}
		}    	
    }


}
	
		
	
	
	
		
		
	
    
