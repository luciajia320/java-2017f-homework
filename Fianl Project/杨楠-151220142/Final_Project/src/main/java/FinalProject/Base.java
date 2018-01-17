package FinalProject;

public class Base extends Type{
	private int basekind;
	private int base_cross_kind = 3;
	public Base() {
		basekind = 0;
	}
	@Override
	public void settype(int n) {
		basekind = n;
	}
	@Override
	public int gettype() {
		return basekind;
	}
	public void setposition(int x,int y,int t) {
		row = x;
		col = y;
		Stage.ground_below[x][y].settype(t);
	}
	public boolean able_cross() {
		if(basekind<base_cross_kind)
			return true;
		else
			return false;
	}
}
