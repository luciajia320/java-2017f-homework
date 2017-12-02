
public class Grandfather implements Creature {
	String matter;
	public Grandfather(String matter) {
		this.matter = matter;
	}
	
	@Override
	public String getMatter() {
		return matter;
	}
	
	@Override
	public void report() {
		System.out.print("@үү");
	}
}
