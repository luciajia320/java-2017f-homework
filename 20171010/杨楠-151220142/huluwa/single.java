package huluwa;

public class single {
	protected creature s;
	public single() {
		s = new creature();
	}
	public void setposition(int stx,int sty) {
		s.setposition(stx, sty);
	}
	public void leaveposition() {
		s.leaveposition();
	}
}
