package huluwa;

public class stage {
	public static type [][] ground;
	private static int size;
	public stage(int n) {
		size = n;
		ground = new type [size][size];
		for(int i=0;i<size;i++)
			for(int j=0;j<size;j++) {
				type t = new type();
				ground[i][j] = t;
			}
	}
	public static int getsize() {
		return size;
	}
	public void show() {
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) 
				ground[j][i].showname();
			System.out.println(" ");
			}
	}
}
