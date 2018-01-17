package huluwa3;

public interface Creature {

	public void report();
	public void printCreature();//在站队中打印形象
	public Position<Creature> getPosition();
	public void setPosition(Position<Creature> position);
	public boolean smallerThan(Creature creature,int type);
	public void tellMove(Position<Creature> a,Position<Creature> b);
	
	
}
