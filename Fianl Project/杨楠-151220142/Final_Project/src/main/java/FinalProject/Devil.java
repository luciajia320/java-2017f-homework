package FinalProject;

public class Devil extends Group{
	private int hp;
	private int damage;
	public Devil(int n,Step s) {
		super(n,s,12);
		hp = 3;
		damage = 2;
		for(int i=0;i<n;i++) {
			group[i].settype(12);
			group[i].setpower(3,2);
		}
	}
}