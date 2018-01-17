package example;

public class Grandpa implements Creature {
	private Position position;
	@Override
	public void setPosition(Position position) {
		this.position = position;
	}
	@Override
	public Position getPosition() {
		return this.position;
	}
	@Override
	public String toString() {
		return "爷爷";
	}
	@Override
	public String report() {
		return "爷爷";
	}
	@Override
	public void resetPosition(Position position)
	{
		position.setHolder(null);
	}
	public void Refueling() {
		
	}
}
