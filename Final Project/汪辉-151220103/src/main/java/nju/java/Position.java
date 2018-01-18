package main.java.nju.java;

public class Position extends Tile{
	private boolean is_has2Dthing=false;
	private int hurt=0;
	private boolean is_goodboy=true;
	public Position(int x,int y) {
		super(x,y);
	}
	public void SetPosition(HuluBro a) {
		this.is_has2Dthing=true;
		this.hurt=a.getHurt();
		is_goodboy=true;
	}
	public void SetPosition(BadBoy a) {
		this.is_has2Dthing=true;
		this.hurt=a.getHurt();
		is_goodboy=false;
	}
	public void SetPosition(Bullet a) {
		this.is_has2Dthing=true;
		this.hurt=a.getHurt();
		is_goodboy=true;
	}
	public boolean is_hasThing() {
		return is_has2Dthing;
	}
	public boolean is_goodboy() {
		return is_goodboy;
	}
	public int getHurt() {
		return hurt;
	}
	public void ResetPosition() {
		this.is_has2Dthing=false;
		this.hurt=0;
		this.is_goodboy=true;
	}
	public void SetPosition(Player player) {
		// TODO Auto-generated method stub
		
	}
}
