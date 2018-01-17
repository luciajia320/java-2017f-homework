package FinalProject;

public class Xiezi extends Single{
	private int hp;
	private int damage;
	public Xiezi(Step s) {
		super(9,s);
		hp = 2;
		damage = 1;
		single.settype(10);
		single.setpower(2, 1);
	}
}
