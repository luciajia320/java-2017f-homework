
public class Position <T extends Creature>{
	private T holder;

	public T getHolder() {
		return holder;
	}

	public void setHolder(T holder) {
		this.holder = holder;
	}

	public void clearHolder() {
		this.holder = null;
	}
}
