package huluwa2;

public class Space {
	public static int N = 20;
	private Position[][] positions;

	Position space(){
	 positions = new Position[N][N];
	 int i,j;
	 for(i=0;i<N;i++) {
		 for(j=0;j<N;j++) {
			 positions[i][j]=new Position(i,j);
		 }
	 }
	 public Position getPosition(int x, int y)
	 {
		 return positions[x][y];
	 }
	
}

	public void Output() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(!positions[i][j].Empty()) {
					positions[i][j].getHolder().report();
					System.out.print("Î»ÖÃÎª¿Õ");
				}
				else 
				{
					System.out.print("   ");
				}
			}
		}
	}
}
