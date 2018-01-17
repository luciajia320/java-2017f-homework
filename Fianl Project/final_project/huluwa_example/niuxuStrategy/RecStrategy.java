package niuxuStrategy;

public class RecStrategy extends strategy {

	public RecStrategy() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public RecStrategy(boolean isKind) {
		super();
		if(isKind) {
			this.getStra()[2][3]=4;
			this.getStra()[2][4]=5;
			this.getStra()[3][2]=3;
			this.getStra()[3][5]=6;
			this.getStra()[4][2]=2;
			this.getStra()[4][5]=7;
			this.getStra()[5][3]=1;
			this.getStra()[5][4]=8;
		}
		else {
			this.getStra()[10][3]=1;
			this.getStra()[10][4]=2;
			this.getStra()[11][2]=1;
			this.getStra()[11][5]=1;
			this.getStra()[12][2]=1;
			this.getStra()[12][5]=1;
			this.getStra()[13][3]=1;
			this.getStra()[13][4]=1;
		}
	}

}
