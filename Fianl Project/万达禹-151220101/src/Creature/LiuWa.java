package Creature;

import HuluTeam.BattleField;

public class LiuWa extends HuluBoy{

	public LiuWa(){
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
		info += ("���ޱ���޷�ָ���ˣ�\r\n\r\n");
		for(int i=0;i<f.ene.enemies.size();i++) {
			f.ene.enemies.get(i).ifdodge = true;
		}
		return info;
	}
	
}
