
public class Scorpion implements Creature {
	String matter;
	public Scorpion(String matter) {
		this.matter = matter;
	}
	
	@Override
	public String getMatter() {
		return matter;
	}
	
	@Override
	public void report() {
		System.out.print("@Ð«×Ó¾«");
	}
}
