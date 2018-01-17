package nju.hulu;
import java.util.ArrayList;
import java.util.Random;

public class Queue<T extends Creature> {	
	
	private ArrayList<Position<T>> pos=new ArrayList<Position<T>>();
	private ArrayList<T> creatures;
	
	
	@SuppressWarnings("unchecked")
	public Queue(ArrayList<T> creaturesToSet)
	{
		this.creatures=creaturesToSet;

		for (int i=0;i<creatures.size() ;i++)
		{
			
			pos.add((Position<T>) creatures.get(i).getPosition());
			
		}
		
	}
	
	public ArrayList<Position<T>> getPositions()
	{
		return pos;
	}
	

	public void setFirst(Position<Creature> pos)
	{
		creatures.get(0).setPosition(pos);
	}
	
	public ArrayList<T> getCreatures()
	{
		return creatures;
	}

}
