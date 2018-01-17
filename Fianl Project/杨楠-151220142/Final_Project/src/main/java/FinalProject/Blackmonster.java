package FinalProject;

public class Blackmonster extends Group{
	private int hp;
	private int damage;
	public Blackmonster(int n,Step s) {
		super(n,s,14);
		hp = 2;
		damage = 1;
		for(int i=0;i<n;i++) {
			group[i].settype(14);
			group[i].setpower(2, 1);
		}
	}
}