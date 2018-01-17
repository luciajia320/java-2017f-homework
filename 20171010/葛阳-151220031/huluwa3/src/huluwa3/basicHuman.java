package huluwa3;

public abstract class basicHuman implements Creature{

	Position<Creature> position;
	@Override
	public Position<Creature> getPosition()
	{
		return position;
	}
	
	@Override
	public void setPosition(Position<Creature> positionToSet)
	{
		this.position.exist=false;
		this.position=positionToSet;
		this.position.setHolder(this);
	}
	
	@Override
	public void tellMove(Position<Creature> a,Position<Creature> b)//¸æÖªÒÆÎ»
	{
		this.report();
		System.out.print(" :");
		a.printPosition();
		System.out.print("->");
		b.printPosition();
		System.out.println();
	}
	@Override
	public boolean smallerThan(Creature creature,int type)
	{
		return false;
	}
	basicHuman()
	{
		position=new Position<Creature>(0,0);
	}
}
