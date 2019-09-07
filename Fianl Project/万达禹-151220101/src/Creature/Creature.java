package Creature;

import HuluTeam.*;

public class Creature {
	
	private String name;
	public int HP;
	public int currentHP;
	private int ATK;
	public Position position;
	public boolean iftaunt;	//AI�ж��Ƿ����޳���
	public boolean ifdodge;	//AI�ж������Ƿ�����
	
	Creature(String n, int h, int a){
		this.name = n;
		this.HP = h;
		this.ATK = a;
		this.currentHP = h;
		this.iftaunt = false;
		this.ifdodge = false;
	}
	
	public String getname() {
		return this.name;
	}
	
	public void report() {
		System.out.println(this.toString());
	}
	
	public void setPosition(int x, int y) {
		Position p = new Position(x, y);
		this.position = p;
	}
	
	public Position getPosition() {
		return this.position;
	}
	
	public String toString() {
		 return "name:"+this.name+"("+this.position.GetPositionX()+","+this.position.GetPositionY()+") HP:"+this.currentHP+" ATK:"+this.ATK;
	}
	
	public String attack(BattleField f, int x, int y) {
		String info="";
		f.field[x*f.colnum+y].setVisible(true);
		f.repaint();
		if(f.field[x*f.colnum+y].reportHolder()==false)
			return info;
		else if(this.currentHP == 0) {
			info += (this.toString()+":\r\n���������޷�����\r\n\r\n");
			return info;
		}
		else {
			Creature other = f.field[x*f.colnum+y].GetHolder();
			info += (name+"("+this.position.GetPositionX()+" , "+this.position.GetPositionY()+")�����ˣ�\r\n"+other.toString()+"\r\n");
			other.currentHP = other.currentHP - this.ATK;
			if(other.currentHP <= 0) {
				other.currentHP = 0;
				info += (other.toString()+"����������\r\n");
				f.repaint();
			}
			info += ("����ˣ� "+ATK+"���˺�\r\n\r\n");
			return info;
		}
	}
	
	public String recover(BattleField f,  int covervalue) {
		String info="";
		if(this.currentHP == 0)
			info += (name+"���������޷�����\r\n");
		else{
			if(this.currentHP+covervalue >= this.HP) {
				info += (name+"�ظ���"+(this.HP-this.currentHP)+"������ֵ\r\n");
			}
			else info += (name+"�ظ���"+covervalue+"������ֵ\r\n");
		}
		return info;
	}
	
	public String skill(BattleField f, int x, int y) {
		return null;
	}
	
}
