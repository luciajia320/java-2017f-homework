package huluwa3;

public class ChangShe implements positionSetter{
	@Override
	public void setPosition(Queue<? extends Creature> queue,Position<Creature>[][] positions)
	{
		Creature[] creatures=queue.getCreatures();		
		Position<Creature> first=creatures[0].getPosition();
		
		for (int i=1;i<creatures.length;i++)
		{
			creatures[i].setPosition(positions[first.getX()+i][first.getY()]);
		}
	}

}
