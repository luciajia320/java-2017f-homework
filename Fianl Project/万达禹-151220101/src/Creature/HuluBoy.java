package Creature;

public class HuluBoy extends Creature{
	
	public HuluBoy(String n, int h, int a){
		super(n,h,a);
	}
	
	public void SetPosition(int x, int y) {
		super.setPosition(x, y);
		position.SetHolder(this);
	}
	
}