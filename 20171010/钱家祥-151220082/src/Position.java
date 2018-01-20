
public class Position {
	private boolean isCreature;
	private int x;
	private int y;
	private Creature holder;
	
	Position(int x, int y) {
		this.x = x;
		this.y = y;
		this.isCreature = false;
	}
	
	public boolean isCreature() {
		return isCreature;
	}

	public void setCreature(boolean isCreature) {
		this.isCreature = isCreature;
	}

	public Creature getHolder() {
		return holder;
	}
	public void setHolder(Creature holder) {
		this.holder = holder;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
}
