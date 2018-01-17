package huluwa2;

public class Position {
	public static int no;
	public int x,y;
	public Creature holder;
	private boolean empty;
	public Position(int x,int y) {
		this.x= x;
		this.y= y;
		
	}
	public boolean Empty() {
		return empty;
	}
	public void setX( int x) {
		this.x =x;
	}
	public void setY (int y) {
		this.y=y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Creature out() {
		Creature holder = this.holder;
		this.holder=null;
		empty = true;
		return holder;
		
			
	}
	public void setHolder(Creature holder) {
		this.holder=holder;
		empty=false;
	}
	public Creature getHolder() {
		return holder;
	}

}
