package example;

public class Shejing implements Creature{
	private Position position;
	public void setPosition(Position position)
	{
		this.position = position;
	}
	public Position getPosition() {
		return this.position;
	}
	@Override
	public String toString() {
		return "蛇精";
	}
	@Override
	public void resetPosition(Position position)
	{
		position.setHolder(null);
	}
	public void Refueling() {
		
	}
	@Override
	public String report() {
		return "蛇精";
	}
}
