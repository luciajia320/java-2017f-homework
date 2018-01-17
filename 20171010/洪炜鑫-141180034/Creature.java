/**************************************************
	> File Name:  Creature.java
	> Author:     Leuckart
	> Time:       2018-01-05 22:40
**************************************************/

public class Creature
{
	public Position position;

	public void setPosition(Position position)
	{
		this.position=position;
		position.setCreature(this);
	}

	public String toString()
	{
		return "无";
	}

	public Position getPosition()
	{
		return position;
	}
}
