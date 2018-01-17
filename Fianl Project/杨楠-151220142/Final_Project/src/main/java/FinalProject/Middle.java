package FinalProject;

public class Middle extends Type{
	protected int middlekind;
	public Middle() {
		middlekind = 0;
	}
	@Override
	public void settype(int n) {
		middlekind = n;
	}
	@Override
	public int gettype() {
		return middlekind;
	}
	@Override
	public void setposition(int x,int y) {
		row = x;
		col = y;
		Stage.ground_mid[x][y] = this;
	}
	@Override
	public void leaveposition() {
		Middle c = new Middle();
		Stage.ground_mid[row][col] = c;
		row = -1;
		col = -1;
	}
	public int effectness() { // set common items effects here
		if(middlekind == 2)
			return 1;
		else if(middlekind>2)
			return -1;
		else 
			return 0;
	}
	public boolean unable_cross() {
		if(middlekind>100)
			return true;
		else
			return false;
	}
}
