package FinalProject;

public class Medicine extends Middle{
	public Medicine() {
		middlekind = 2;
	}
	@Override
	public int effectness() { //set certain effect 
		return 1;
	}
	@Override
	public boolean unable_cross() {// set certain cross-enable
		return false;
	}
}
