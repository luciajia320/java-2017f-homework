package niuxuStrategy;

public class GapStrategy extends strategy {

	public GapStrategy() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public GapStrategy(boolean isKind) {
		super();
		if(isKind) {
			this.getStra()[4][0]=1;
			this.getStra()[4][2]=2;
			this.getStra()[4][4]=3;
			this.getStra()[4][6]=4;
			this.getStra()[3][1]=5;
			this.getStra()[3][3]=6;
			this.getStra()[3][5]=7;
			this.getStra()[3][7]=8;
		}
		else {
			this.getStra()[11][0]=1;
			this.getStra()[11][2]=1;
			this.getStra()[11][4]=1;
			this.getStra()[11][6]=1;
			this.getStra()[12][1]=1;
			this.getStra()[12][3]=1;
			this.getStra()[12][5]=1;
			this.getStra()[12][7]=2;
		}
	}

}
