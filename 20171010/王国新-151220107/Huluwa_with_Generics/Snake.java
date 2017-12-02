
public class Snake implements Creature {
	String matter;
	
	public Snake(String matter) {
		this.matter = matter;
	}
	@Override
	public String getMatter() {
		return matter;
	}
	@Override
	public void report() {
		System.out.print("@Éß¾«");
	}
}
