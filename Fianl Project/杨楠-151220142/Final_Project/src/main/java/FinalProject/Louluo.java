package FinalProject;

public class Louluo extends Group{
	private int hp;
	private int damage;
	public Louluo(int n,Step s) {
		super(n,s,11);
		hp = 1;
		damage = 1;
		for(int i=0;i<n;i++) {
			group[i].settype(11);
			group[i].setpower(1, 1);
		}
	}
}