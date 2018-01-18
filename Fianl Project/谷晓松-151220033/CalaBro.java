package com.gxs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

enum Name
{
	 红娃,橙娃,黄娃,绿娃,青娃,蓝娃,紫娃;
}
enum Color
{
	赤, 橙, 黄, 绿, 青, 蓝, 紫 ;
}
enum Rank
{
	一,二,三,四,五,六,七;
}

public class CalaBro extends Soldier
{
	private Color color;
	private Rank rank;
	private Position pos;
	
	private static final int SPEED=1;
	private static final int POWER=10;
	
	private CalaBro(String name,Color color,Rank rank)
	{
		super(name,SPEED,POWER,Camp.GOOD);
		this.color=color;
		this.rank=rank;
	}
	//private static final CalaBro[] brothers=new CalaBro[7];
	private static  final ArrayList<CalaBro>brothers=new ArrayList<CalaBro>();
	
	static 
	{
		for(int i=0;i<7;i++)
		{
			brothers.add(new CalaBro(Name.values()[i].toString(),Color.values()[i],Rank.values()[i]));
			brothers.get(i).setImage(Constant.IMGPATH+"c"+i+".png");
			//brothers.get(i).setImage("c"+i+".png");
		}
	}
	//public static CalaBro[] getAcess() {return brothers[];}

	public static  ArrayList<CalaBro> getAcess()
	{
		return brothers;
	}
	
}

class Grandpa extends Commander
{
	private static final int SPEED=1;
	private static final int POWER=10;
	
	private Grandpa(String name)
	{
		super(name,SPEED,POWER,Camp.GOOD);
		loadStrategy();
		
	}
	private static Grandpa grandpa=new Grandpa("爷爷");
	
	
	static
	{
		//加载图片语句不能放在构造函数中
		grandpa.setImage(Constant.IMGPATH+"grandpa.png");
	}
	
	public static Grandpa getAcess()
	{
		return grandpa;
	}
	
	@Override
	public String toString()
	{
		return ("👨");
	}
	@Override
	public void loadStrategy()
	{
		this.addStrategy(StrategyFactory.createEb(soldierList, new Position(Constant.LOGIC_HEIGHT/2-3,0), Dir.UP,Shape.Line));
		this.addStrategy(StrategyFactory.createHd(soldierList,20));
		this.addStrategy(StrategyFactory.createEb(soldierList, new Position(Constant.LOGIC_HEIGHT/2,5), Dir.RIGHT, Shape.SmallWing));
		this.addStrategy(StrategyFactory.createHd(soldierList,20));
		//this.addStrategy(StrategyFactory.createMc(soldierList, 1,10,Dir.RIGHT));
		this.addStrategy(StrategyFactory.createWd(soldierList));
	}
	/*
	@Override
	public void makeStrategy()
	{ 
		
	}
	*/
}