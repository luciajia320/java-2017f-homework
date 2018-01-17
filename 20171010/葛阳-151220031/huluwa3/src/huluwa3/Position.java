package huluwa3;

public class Position<T extends Creature> {

	private int x,y;
	Creature holder;
	boolean exist;
	
	Position (int x1,int y1)
	{
		exist=false;
		x=x1;
		y=y1;
		holder=null;
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public void setPosition(int x1,int y1)
	{
		this.x=x1;
		this.y=y1;
	}
	Creature getHolder()
	{
		if (exist)
			return holder;
		else
		{
			System.out.println("no Holder");
			return null;
		}
	}
	
	public void setHolder(T holder1)
	{
		this.exist=true;
		holder=holder1;
	}
	
	public void printPosition()
	{
		System.out.print("("+x+","+y+")");
	}
	
	public boolean equal(Position<T> pos)
	{
		if (this.x==pos.x&&this.y==pos.y)
			return true;
		else
			return false;
	}
	
	public void printHolder() 
	{
		if (this.exist==false)
			System.out.print("🌲");
		else
			this.holder.printCreature();
	}
	
	public void setNull()
	{
		this.exist=false;
	}
	
}
