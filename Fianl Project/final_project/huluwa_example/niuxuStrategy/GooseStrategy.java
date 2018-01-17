package niuxuStrategy;

public class GooseStrategy extends strategy {

	public GooseStrategy() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public GooseStrategy(boolean isKind) {
		super();
		if(isKind) {
			this.getStra()[2][1]=1;
			this.getStra()[3][2]=2;
			this.getStra()[4][3]=3;
			this.getStra()[5][4]=4;
			this.getStra()[1][3]=5;
			this.getStra()[2][4]=6;
			this.getStra()[3][5]=7;
			this.getStra()[4][6]=8;
		}
		else {
			this.getStra()[10][4]=1;
			this.getStra()[11][3]=1;
			this.getStra()[12][2]=1;
			this.getStra()[13][1]=1;
			this.getStra()[11][6]=2;
			this.getStra()[12][5]=1;
			this.getStra()[13][4]=1;
			this.getStra()[14][3]=1;
		}
	}

}
