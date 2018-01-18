package example;

public class Xiezi implements Creature{
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
		return "蝎子";
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
		return "蝎子";
	}
}
