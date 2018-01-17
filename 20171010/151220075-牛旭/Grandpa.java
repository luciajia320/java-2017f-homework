
public class Grandpa extends Creature implements Boss {
	public Grandpa() {
		super("Grandpa");
	}
	@Override
	public void encourage() {
		System.out.println("Come on! HuLuWa! Come on!");
	}
	@Override
	public void strengthUp() {
		System.out.println("My HuLuWas become bigger and stronger.");
	}
	@Override
	public void report() {
		System.out.print("#");
	}
}
