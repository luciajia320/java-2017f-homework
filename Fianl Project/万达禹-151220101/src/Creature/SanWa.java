package Creature;

import HuluTeam.BattleField;

public class SanWa extends HuluBoy{

	public SanWa(){
		super("����",1500,100);
	}
	
	@Override
	public String skill(BattleField f,int x, int y) {
		String info = "";
		if(super.currentHP == 0) {
			info += ("�����������޷�����\r\n");
			return info;
		}
		info += super.attack(f, x, y);
		info += ("���������˵ط�ȫ���ע��\r\n\r\n");
		for(int i=0;i<f.ene.enemies.size();i++) {
			f.ene.enemies.get(i).iftaunt = true;
		}
		return info;
	}
	
}
