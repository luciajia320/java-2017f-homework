
public class XiaoLouluo implements Creature {
	String matter;
	public XiaoLouluo(String matter) {
		this.matter = matter;
	}
	
	@Override
	public String getMatter() {
		return matter;
	}
	
	@Override
	public void report() {
		System.out.print("@à¶†ª");
	}
}
