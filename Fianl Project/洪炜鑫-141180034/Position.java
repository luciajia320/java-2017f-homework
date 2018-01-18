/**************************************************
	> File Name:  Position.java
	> Author:     Leuckart
	> Time:       2018-01-05 22:41
**************************************************/

public class Position
{
	private Creature creature;
	private int x;
	private int y;

	public void setCreature(Creature creaturer)
	{
		this.creature=creature;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public Position()
	{
		this.x=0;
		this.y=0;
	}

	public Position(int x, int y)
	{
		this.x=x;
		this.y=y;
	}
}
