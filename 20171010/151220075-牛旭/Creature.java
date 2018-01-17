
public class Creature {
	private String name;
	public Creature(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void report() {
		System.out.print("*");
	}
}