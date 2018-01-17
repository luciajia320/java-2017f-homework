
public class Snake extends Creature implements Boss {
	public Snake() {
		super("Snake");
	}
	@Override
	public void encourage() {
		System.out.println("Come on, my guys! Beat them!");
	}

	@Override
	public void strengthUp() {
		System.out.println("All bad guys become crueller and more violent.");
	}
	@Override
	public void report() {
		System.out.print("$");
	}
}
