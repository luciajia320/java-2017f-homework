package FinalProject;

public class Huluwa extends Group{
	private static int[] kids = {1,2,3,4,5,6,7};
	private int hp;
	private int damage;
	private static int kinds = 7;
	public Huluwa(int n,Step s) {
		super(n,s,0);
		hp = 3;
		damage = 1;
		for(int i=0;i<n;i++) {
			group[i].settype(kids[i]);
			group[i].setpower(3, 1);
		}
	}
	public static int getkinds() {
		return kinds;
	}
}