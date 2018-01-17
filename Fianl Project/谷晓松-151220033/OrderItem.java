package com.gxs;



public class OrderItem
{
	
}
class MoveToOrder extends OrderItem
{
	private Position dest;
	MoveToOrder(Position dest)
	{
		this.dest=dest;
	}
	public Position getDest() {	return dest;}
}
class FreeOrder extends OrderItem
{
}
class WinOrDieOrder extends OrderItem
{
}
class MarchOrder extends OrderItem
{
	private int speed;
	private Dir dir;
	MarchOrder(int speed,Dir dir)
	{
		this.speed=speed;
		this.dir=dir;
	}
	public int getSpeed() {	return speed;	}
	public Dir getDir() {	return dir;}
}
class HoldOrder extends OrderItem
{
	
}
class OrderFactory
{
	public static MoveToOrder createMt(Position dest)
	{
		return new MoveToOrder(dest);
	}
	public static FreeOrder createFr()
	{
		return new FreeOrder();
	}
	public static HoldOrder createHd()
	{
		return new HoldOrder();
	}
	public static MarchOrder createMc(int speed,Dir dir)
	{
		return new MarchOrder(speed,dir);
	}
	public static WinOrDieOrder createWD()
	{
		return new WinOrDieOrder();
	}
}
