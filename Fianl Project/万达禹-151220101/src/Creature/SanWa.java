package Creature;

import HuluTeam.BattleField;

public class SanWa extends HuluBoy{

	public SanWa(){
		super("三娃",1500,100);
	}
	
	@Override
	public String skill(BattleField f,int x, int y) {
		String info = "";
		if(super.currentHP == 0) {
			info += ("三娃已死亡无法攻击\r\n");
			return info;
		}
		info += super.attack(f, x, y);
		info += ("三娃引起了地方全体的注意\r\n\r\n");
		for(int i=0;i<f.ene.enemies.size();i++) {
			f.ene.enemies.get(i).iftaunt = true;
		}
		return info;
	}
	
}
