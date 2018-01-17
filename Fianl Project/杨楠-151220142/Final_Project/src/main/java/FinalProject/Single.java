package FinalProject;

public class Single{
	protected Creature single;
	public Single() {
		single = new Creature();
	}
	public Single(int index,Step s) {
		single = new Creature(index,s);
	}
	public void setposition(int stx,int sty) {
		single.setposition(stx, sty);
	}
	public void leaveposition() {
		single.leaveposition();
	}
	public void start() {
		single.start();
	}
}