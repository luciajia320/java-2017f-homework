package assignment3;

class Position {
	
	private int x;
	private int y;
	Creature creature;
	boolean empty;
	
	Position()
	{
		x = 0;
		y = 0;
		empty = true;
	}
	
	Position(int x,int y)
	{
		this.x = x;
		this.y = y;
		empty = true;
	}
	
	public void setCreature(Creature c)
	{
		if(c == null)
			empty = true;
		else
		{
			empty = false;
			creature = c;
		}
	}
	public Creature getCreature()
	{
		return creature;
	}
	boolean isEmpty()
	{
		if(empty == true)
			return true;
		else
			return false;
	}
	void setEmpty()
	{
		empty = true;
	}
	
	int getX()
	{
		return x;
	}
	int getY()
	{
		return y;	
	}
	void setX(int x)
	{
		this.x = x;
	}
	void setY(int y)
	{
		this.y = y;
	}
}
