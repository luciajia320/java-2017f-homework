package Creature;

import HuluTeam.BattleField;

public class WuWa extends HuluBoy{

	public WuWa(){
		super("����",600,100);
	}
	
	@Override
	public String skill(BattleField f,int x, int y) {//����һ���еĵ���
		String info="";
		if(super.currentHP == 0) {
			info += ("���������޷�����\r\n");
			return info;
		}
		info += ("����ʹ���˴��ٲ�֮���������ˣ�\r\n");
		for(int i=0;i<f.rownum;i++) {
				info += super.attack(f, i, y);
		}
		return info;
	
	}
	
}
