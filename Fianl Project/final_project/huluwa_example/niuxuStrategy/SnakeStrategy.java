package niuxuStrategy;

public class SnakeStrategy extends strategy {

	public SnakeStrategy() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public SnakeStrategy(boolean isKind) {
		super();
		if(isKind) {
			this.getStra()[2][0]=1;
			this.getStra()[2][1]=2;
			this.getStra()[2][2]=3;
			this.getStra()[2][3]=4;
			this.getStra()[2][4]=5;
			this.getStra()[2][5]=6;
			this.getStra()[2][6]=7;
			this.getStra()[2][7]=8;
		}
		else {
			this.getStra()[13][0]=1;
			this.getStra()[13][1]=1;
			this.getStra()[13][2]=1;
			this.getStra()[13][3]=1;
			this.getStra()[13][4]=1;
			this.getStra()[13][5]=1;
			this.getStra()[13][6]=1;
			this.getStra()[13][7]=2;
		}
	}
}