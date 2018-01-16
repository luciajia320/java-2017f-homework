package assignment3;

abstract class Creature {
	
	private Position pos;
	
	Creature()
	{
		
	}
	
	public void setPos(Position pos)
	{
		this.pos = pos;
	}
	public Position getPos()
	{
		return pos;
	}
	
	public abstract String report();
	
}
