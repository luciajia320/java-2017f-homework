
public class Position {
	private boolean empty;
	private Creature content;
	public Position() {
		empty=true;
		content=null;
	}
	public boolean isEmpty() {
		return empty;
	}
	public void setContent(Creature x) {
		empty=false;
		content=x;
	}
	public void showContent() {
		if(isEmpty()) {
			System.out.print(" ");
		}
		else
			content.report();
	}
}
