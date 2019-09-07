package Creature;

import HuluTeam.BattleField;

public class QiWa extends HuluBoy{

	public QiWa(){
		super("����",1000,100);
	}
	
	@Override
	public String skill(BattleField f,int x, int y) {
		String info = "";
		if(super.currentHP == 0) {
			info += ("�����������޷�����\r\n");
			return info;
		}
		info += super.attack(f, x, y);
		info += ("����Ϊȫ��ظ���30��������\r\n");
		for(HuluBoy a : f.hulu.hulubrothers) {
			info += a.recover(f, 30);
		}
		info += "\n";
		return info;
	}
	
}
