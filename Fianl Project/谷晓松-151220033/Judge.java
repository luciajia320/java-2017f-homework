package com.gxs;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;
import java.io.*;

public class Judge implements Runnable
{
	enum STATES{	INIT,LOAD,RUN, PAUSE,OVER,REPLAY	};
	private STATES state=STATES.INIT;
	private ArrayList<Fighter>evilFighter=new ArrayList<Fighter>();
	private ArrayList<Fighter>goodFighter=new ArrayList<Fighter>();
	private int goodIdCount=0,evilIdCount=0;
	
	private SchedulerList schedulerList=new SchedulerList();
	
	//用于judge与space的同步
	private DrawTimer drawTimer;
	
	private FileRecorder fileRecorder=null;
	
	
	private Judge() {}
	private static Judge judge=new Judge();
	public static Judge getAcess()
	{
		return judge;
	}
	public void setDrawTimer(DrawTimer drawTimer)
	{
		this.drawTimer=drawTimer;
	}
	public void register(Fighter f)
	{
		switch(f.getCamp())
		{
		case GOOD:	f.setPid(goodIdCount++);	goodFighter.add(f);	goodLeft++;	break;
		case EVIL:	f.setPid(evilIdCount++);	evilFighter.add(f);	evilLeft++;	break;
		}
		if(Commander.class.isInstance(f))
		{
			Commander c=(Commander)f;
			c.setScheduler(schedulerList.add());
		}
	}
	
	/*functions to react to commander's strategies*/
	private int goodLeft=0;
	private int evilLeft=0;
	public int getGoodLeft() {	return goodLeft;}
	public int getEvilLeft() {	return evilLeft;}
	public boolean isOver() {	return (goodLeft==0|| evilLeft==0);	}
	
	
	private void process()
	{
		fight();
		over();
	}
	private void over()
	{
		if(this.isOver())
		{
			System.out.println("game over");
			fileRecorder.close();
			state=STATES.OVER;
		}
	}
	private void fight()
	{
		for(Fighter gf: goodFighter)
			for(Fighter ef: evilFighter)
			{
				if(gf.isAlive()&&ef.isAlive()&&Position.equal(gf.getPositon(), ef.getPositon()))
				{
					boolean b=Fighter.Fight(gf, ef);
					if(b)
						evilLeft--;
					else
						goodLeft--;
				}
			}
	}
	private void InitPosition()
	{
		Random rand=new Random();
		for(int i=0;i<goodFighter.size();i++)
			goodFighter.get(i).setPosition(new Position(rand.nextInt(Constant.LOGIC_HEIGHT),rand.nextInt(Constant.LOGIC_WIDTH/2)));
		for(int i=0;i<evilFighter.size();i++)
			evilFighter.get(i).setPosition(new Position(rand.nextInt(Constant.LOGIC_HEIGHT),rand.nextInt(Constant.LOGIC_WIDTH/2)+Constant.LOGIC_WIDTH/2-1));
	}
	private void InitStory()
	{
		ArrayList<CalaBro> cala=CalaBro.getAcess();
		Grandpa	grandpa=Grandpa.getAcess();
		
		for(CalaBro c : cala)
			grandpa.addSoldier(c);
		
		Snake snake=Snake.getAcess();
		Scorpion scorpion=Scorpion.getAcess();
		snake.addSoldier(scorpion);
		Soldier[] Monsters=new Soldier[30];
		Monsters[0]=scorpion;
		for(int i=1;i<25;i++)
		{
			Monsters[i]=new Monster("菜鸟");
			snake.addSoldier(Monsters[i]);
		}
		ArrayList<Creature>creature=Creature.getToRun();
//		InitPosition();
		for(Creature c: creature)
			System.out.println(c.toString()+"  ");
		
		Space.getMap().draw();
	}
	

	private void executeAll()
	{
		fileRecorder=new FileRecorder();
		InitPosition();
		Space.getMap().draw();
		
		try {
			TimeUnit.SECONDS.sleep(1);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		ArrayList<Creature>creature=Creature.getToRun();
		Space map=Space.getMap();
		
		
		new Thread(map).start();
		for(Creature c : creature)
			new Thread(c).start();
		
	}
	
	public void start()
	{
		this.state=STATES.LOAD;
	}
	public boolean isReplay()
	{
		return this.state==STATES.REPLAY;
	}
	public boolean showCreature()
	{
		return !(this.state==STATES.INIT||this.state==STATES.PAUSE);
	}
	private void replay(File f)
	{
		ArrayList<File> af=FileRecorder.getSave(5);
		ArrayList<Creature>ac=Creature.getToRun();
		System.out.println("replay");
		try {
			//for(File f : af)
			{
				ObjectInputStream oi=new ObjectInputStream(new FileInputStream(f));
				Object obj;
				System.out.println(f.getName());
				for(int i=0;i<ac.size();i++)
				{
					InfoNode n=(InfoNode)oi.readObject();
					int cid=n.getId();
					System.out.println(ac.get(cid)+" "+n.getPos().getX()+" "+n.getPos().getY()+" "+n.getAlive());
					ac.get(n.getId()).setPosition(n.getPos().getX(),n.getPos().getY());
				}
				this.state=STATES.REPLAY;
				new Thread(new Painter()).start();
				while((obj=oi.readObject())!=null)
				{
					//System.out.println("1");
					InfoNode node=(InfoNode)obj;
					int cid=node.getId();
					ac.get(cid).setPosition(node.getPos().getX(),node.getPos().getY());
					ac.get(cid).setAlive(node.getAlive());
					System.out.println(ac.get(cid)+" "+node.getPos().getX()+" "+node.getPos().getY()+" "+node.getAlive());
					TimeUnit.MILLISECONDS.sleep(50);
				}
				TimeUnit.SECONDS.sleep(1);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		over();
	}
	public void loadSave(File f)
	{
		replay(f);
	}
	public void restart()
	{
		ArrayList<Creature>creature=Creature.getToRun();
		for(Creature c :	creature)
			c.respawn();
		InitPosition();
		Space.getMap().repaint();
		
	}
	/*functions for commander to get information about battle*/
	public Position getPosition(int pid,Camp camp)
	{
		switch(camp)
		{
		case GOOD:	return goodFighter.get(pid).getPositon();
		case EVIL:	return evilFighter.get(pid).getPositon();
		default:	assert(false);	return null;
		}
	}
	
	public int goodIdNum() {	return goodIdCount;}
	public int evilIdNum() {return evilIdCount;}
	public int enemyIdNum(Camp camp)
	{
		if(camp==Camp.GOOD)
			return evilIdNum();
		else
			return goodIdNum();
	}
	Fighter getEnemy(int i,Camp camp)
	{
		if(camp==Camp.GOOD)
			return evilFighter.get(i);
		else
			return goodFighter.get(i);
	}
	
	public void recordMove(Serializable s)
	{
		this.fileRecorder.recordObject(s);
	}
	public void run()
	{
		try {
			while(!Thread.interrupted())
			{
			//	System.out.println(state);
				switch(state)
				{
					case RUN:
					{
						//等待绘图
						System.out.println("judge waiting for drawing");
						drawTimer.waitForDraw();
						//调度下一步动作
						System.out.println(" judge waing  for move");
						schedulerList.scheduleOneTask();
						//进行处理
						process();
						//处理完成
						drawTimer.moveOver();
						break;
					}
					case INIT:{	InitStory();	state=STATES.PAUSE;	break;}
					case LOAD:
					{
						executeAll();
						state=STATES.RUN;
						break;
					}
					case PAUSE:
					{
					//	break;
					}
					case OVER:
					{
					}
					case REPLAY:
					{
					//	Space.getMap().repaint();
					//	TimeUnit.MILLISECONDS.sleep(20);
						break;
					}
					default:
						break;
				}
			}
		}catch(InterruptedException e)
		{
			System.out.println("judge exits via interruption");
		}
	}
}

class Painter implements Runnable
{
	public void run()
	{
		try {
			while(!Thread.interrupted())
			{
		//		System.out.println("painter");
				Space.getMap().repaint();
				TimeUnit.MILLISECONDS.sleep(30);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
