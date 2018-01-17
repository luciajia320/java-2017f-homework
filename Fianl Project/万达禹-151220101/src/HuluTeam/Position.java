package HuluTeam;

import Creature.Creature;

public class Position {
	
	private int x;
	private int y;
	private Creature holder;
	private boolean isthereCreature;
	private boolean ifVisible;	//ÊÇ·ñ¿É¼û
	
	public Position(int positionx, int positiony) {
		this.x = positionx;
		this.y = positiony;
		this.isthereCreature = false;
		this.ifVisible = true;
	}
	
	public void SetHolder(Creature a) {
		this.holder = a;
		a.setPosition(x, y);
		this.isthereCreature = true;
	}
	
	public boolean getVisible() {
		return this.ifVisible;
	}
	
	public void setVisible(boolean setting) {
		this.ifVisible = setting;
	}
	
	public void DeleteHolder() {
		this.holder = null;
		this.isthereCreature = false;
	}
	
	public void SetPosition(int positionx, int positiony) {
		this.x = positionx;
		this.y = positiony;
	}
	
	public int GetPositionX() {
		return this.x;
	}
	
	public int GetPositionY() {
		return this.y;
	}
	
	public Creature GetHolder() {
		return this.holder;
	}
	
	public boolean reportHolder() {
		return this.isthereCreature;
	}
}
