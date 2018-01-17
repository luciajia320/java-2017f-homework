package niuxuStrategy;

public class ArrowStrategy extends strategy {
	public ArrowStrategy() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public ArrowStrategy(boolean isKind) {
		// TODO Auto-generated constructor stub
		super();
		if(isKind) {
			this.getStra()[1][1]=1;
			this.getStra()[2][2]=2;
			this.getStra()[3][3]=3;
			this.getStra()[3][4]=8;
			this.getStra()[4][4]=4;
			this.getStra()[3][5]=5;
			this.getStra()[2][6]=6;
			this.getStra()[1][7]=7;
		}
		else {
			this.getStra()[11][4]=1;
			this.getStra()[12][3]=1;
			this.getStra()[12][4]=2;
			this.getStra()[12][5]=1;
			this.getStra()[13][2]=1;
			this.getStra()[13][6]=1;
			this.getStra()[14][1]=1;
			this.getStra()[14][7]=1;
		}
	}

}
