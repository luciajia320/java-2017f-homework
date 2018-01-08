package FinalProject;

public class Shejing extends Single{
	private int hp;
	private int damage;
	public Shejing(Step s) {
		super(8,s);
		hp = 2;
		damage = 1;
		single.settype(9);
		single.setpower(2, 1);
	}
}