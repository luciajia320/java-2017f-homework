package com.gxs;

import java.io.File;

public class Monster extends Soldier
{
	private static final int SPEED=1;
	private static final int POWER=1;
	
	private String name;
	
	Monster(String name)
	{
		super(name,SPEED,POWER,Camp.EVIL);
		this.setImage(Constant.IMGPATH+"fish.png");
	}
	
	@Override
	public String toString()
	{
		return ("👿");
	}
}

class Scorpion extends Soldier
{
	private String name;
	private static final int SPEED=1;
	private static final int POWER=5;
	
	private Scorpion(String name)
	{
		super(name,SPEED,POWER,Camp.EVIL);
	
	}
	private static Scorpion scorpion=new Scorpion("蝎子");
	
	static
	{
		scorpion.setImage(Constant.IMGPATH+"scorpion.png");
	}
	
	public static Scorpion getAcess()
	{
		return scorpion;
	}
	
	@Override
	public String toString()
	{
		return ("👹");
	}
}
class Snake extends Commander
{
	private String name;
	private static final int SPEED=1;
	private static final int POWER=10;
	
	private Snake(String name)
	{
		super(name,SPEED,POWER,Camp.EVIL);
		loadStrategy();
	}
	private static Snake snake=new Snake("蛇精");
	
	static
	{
		snake.setImage(Constant.IMGPATH+"snake.png");
	}
	public static Snake getAcess()
	{
		return snake;
	}
	
	@Override
	public String toString()
	{
		return ("🐍");
	}
	@Override
	public void loadStrategy()
	{
		this.addStrategy(StrategyFactory.createEb(soldierList, new Position(Constant.LOGIC_HEIGHT/2,Constant.LOGIC_WIDTH/3*2), Dir.LEFT,Shape.Moon));
		this.addStrategy(StrategyFactory.createHd(soldierList, 40));
	//	this.addStrategy(StrategyFactory.createMc(soldierList,1,3,Dir.LEFT));
		this.addStrategy(StrategyFactory.createWd(soldierList));
	}
	
	
	/*
	@Override
	public void makeStrategy()
	{
		
	}
	*/
}