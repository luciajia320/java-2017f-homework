package niuxuStrategy;

public class strategy {
	private int[][] stra;
	public strategy() {
		// TODO Auto-generated constructor stub
		stra = new int[16][8];
		for(int i=0; i<16; i++) {
			for (int j=0; j<8; j++) {
				stra[i][j]=0;
			}
		}
	}
	public int[][] getStra() {
		return stra;
	}
}
