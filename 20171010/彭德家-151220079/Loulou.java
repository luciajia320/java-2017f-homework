package example;

public class Loulou implements Creature {
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
		return "喽喽";
	}
	@Override
	public String report() {
		return "喽喽";
	}
	@Override
	public void resetPosition(Position position)
	{
		position.setHolder(null);
	}
}
