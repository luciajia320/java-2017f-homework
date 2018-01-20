package FinalProject;

public class Redmonster extends Group{
	private int hp;
	private int damage;
	public Redmonster(int n,Step s) {
		super(n,s,13);
		hp = 2;
		damage = 1;
		for(int i=0;i<n;i++) {
			group[i].settype(13);
			group[i].setpower(2, 1);
		}
	}
}