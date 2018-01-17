package Creature;

import HuluTeam.BattleField;

public class QiWa extends HuluBoy{

	public QiWa(){
		super("七娃",1000,100);
	}
	
	@Override
	public String skill(BattleField f,int x, int y) {
		String info = "";
		if(super.currentHP == 0) {
			info += ("七娃已死亡无法攻击\r\n");
			return info;
		}
		info += super.attack(f, x, y);
		info += ("七娃为全体回复了30点生命！\r\n");
		for(HuluBoy a : f.hulu.hulubrothers) {
			info += a.recover(f, 30);
		}
		info += "\n";
		return info;
	}
	
}
