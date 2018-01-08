package huluwa;

public class creature {
	private int row;
	private int col;
	private type ty;
	public creature() {
		row = -1;
		col = -1;
		ty = new type();
	}
	public int gettype() {
		return ty.gettype();
	}
	public void settype(int i) {
		ty.settype(i);
	}
	public void setposition(int x,int y) {
		row = x;
		col = y;
		stage.ground[x][y].settype(ty.gettype());
		//ty.showname();
	}
	public void leaveposition() {
		stage.ground[row][col].settype(-1);
		//ty.showname();
		row = -1;
		col = -1;
	}
}
