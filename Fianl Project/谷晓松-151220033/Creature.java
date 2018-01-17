package com.gxs;
import java.util.Random;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import java.net.*;


abstract public class Creature implements Runnable
{
	private Position pos;
	private Dir dir;
	private String name;
	private int speed;
	private int power;
	private boolean isAlive=true;
	private int pid;
	private Image img=null;
	
	//use for record
	private int cid;
	private static Random rand=new Random();
	
	private static int IdCounter=0;
	
	private static ArrayList<Creature>toRun=new ArrayList<Creature>();
	public static ArrayList<Creature> getToRun()
	{
		return toRun;
	}
	
	Creature(String name,int speed,int power)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
	{
		this.name=name;
		this.speed=speed;
		this.power=power;
		
		//add this creature for thread
		this.cid=IdCounter++;
		toRun.add(this);
	}
	public String getName()
	{
		return name;
	}
	final protected Position getPositon()
	{
		return pos;
	}
	final protected void setPosition(int x,int y)
	{
		this.pos=new Position(x,y);
	}
	final protected void setPosition(Position pos)
	{
		this.pos=pos;
		
		if(!Judge.getAcess().isReplay())
			Judge.getAcess().recordMove(new InfoNode(this.cid,new Position(pos.getX(),pos.getY())));
	}
	final protected void leavePosition()
	{
	//	this.pos.freeHolder();
		this.pos=null;
	}
	final protected void setDir(Dir dir)
	{
		this.dir=dir;
	}
	final protected int getCid()
	{
		return this.cid;
	}
	final protected Dir getDir()
	{
		return this.dir;
	}
	final public int getPower()
	{
		return power;
	}
	final public int getSpeed()
	{
		return speed;
	}
	final void setAlive(boolean alive)
	{
		this.isAlive=alive;
	}
	final protected boolean isAlive()
	{
		return isAlive;
	}
	final protected void setPid(int pid)
	{
		this.pid=pid;
	}
	final protected int getPid()
	{
		return pid;
	}
	final protected void setImage(String path)
	{
		
		try {
			img=new ImageIcon(path).getImage();
			if(img==null)
				System.out.println("fial to load img");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		/*
		URL loc=this.getClass().getClassLoader().getResource(path);
		ImageIcon iis=new ImageIcon(loc);
		this.img=iis.getImage();
		*/
	}
	final protected void respawn()
	{
		this.isAlive=true;
	}
	final protected Image getImage()
	{
		return img;
	}
	protected void killSelf()
	{
		isAlive=false;
		System.out.println(name+ "is dead");
	}
	//may override for different creatures
	protected void moveTo(Position dest)
	{
		if(Position.equal(dest, pos))
			return;
		int dx=dest.getX()-pos.getX(),dy=dest.getY()-pos.getY();
		int x=Math.abs(dx)>speed?speed*dx/Math.abs(dx):dx,d=speed-Math.abs(x);
		int y=d>0?(d>=Math.abs(dy)?dy:d*dy/Math.abs(dy)):0;
		pos.move(x,y,this.cid);
	}
	final Position getPhyPos()
	{
		return Position.phyPos(pos);
	}
	protected void freeMove()
	{
		int d1=rand.nextInt(2)-1,d2=rand.nextInt(2)-1,s=rand.nextInt(speed);
		pos.move(d1*s,d2*(speed-s),this.cid);
	}
	protected void march(int speed,Dir dir)
	{
		switch(dir)
		{
		case UP:	pos.move(-speed, 0,this.cid);		break;
		case DOWN:	pos.move(speed, 0,this.cid);	break;
		case LEFT:	pos.move(0, -speed,this.cid);		break;
		case RIGHT:	pos.move(0, speed,this.cid);		break;
		}
	}
}
