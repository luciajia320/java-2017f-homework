package Creature;

import HuluTeam.BattleField;

public class LiuWa extends HuluBoy{

	public LiuWa(){
		super("六娃",1000,100);
	}
	
	@Override
	public String skill(BattleField f,int x, int y) {
		String info = "";
		if(super.currentHP == 0) {
			info += ("六娃已死亡无法攻击\r\n");
			return info;
		}
		info += super.attack(f, x, y);
		info += ("六娃变得无法指定了！\r\n\r\n");
		for(int i=0;i<f.ene.enemies.size();i++) {
			f.ene.enemies.get(i).ifdodge = true;
		}
		return info;
	}
	
}
