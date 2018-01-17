package com.gxs;

import java.io.*;

public class Position implements Serializable
{
	private int x;
	private int y;
	
	Position(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	public int getX() { return this.x; }
	public int getY() { return this.y; }
	
	public static boolean equal(Position p1,Position p2)
	{
		return ((p1.getX()==p2.getX())&&(p1.getY()==p2.getY()));
	}
	//将postion与文件读取耦合在一起似乎不恰当 但是也许比较简单
	public void move(int dx,int dy,int id)
	{
		int tx=x+dx>=Constant.LOGIC_HEIGHT-1?Constant.LOGIC_HEIGHT-1:x+dx;
		x=x+dx<=0?0:tx;
		int ty=y+dy>=Constant.LOGIC_WIDTH-1?Constant.LOGIC_WIDTH-1:y+dy;
		y=y+dy<=0?0:ty;
		
		if(!Judge.getAcess().isReplay())
			Judge.getAcess().recordMove(new InfoNode(id,new Position(this.x,this.y)));
	}
	public static Position phyPos(Position pos)
	{
		return new Position(pos.x*Constant.CREATURE_HEIGHT,pos.y*Constant.CREATURE_WIDTH);
	}
	public String toString()
	{
		return new String(x+" "+y);
	}
	public static int getDistance(Position p1,Position p2)
	{
		return Math.abs(p1.x-p2.x)+Math.abs(p1.y-p2.y);
	}
}
