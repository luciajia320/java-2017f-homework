package Creature;

import HuluTeam.BattleField;

public class SiWa extends HuluBoy{
	
	public SiWa(){
		super("����",600,100);
	}
	
	@Override
	public String skill(BattleField f,int x, int y) {//����һ���еĵ���
		String info = "";
		if(super.currentHP == 0) {
			info += ("�����������޷�����\r\n");
			return info;
		}
		info += ("����ʹ���˺�����֮���������ˣ�\r\n");
		for(int i=super.position.GetPositionX()+1;i<f.colnum;i++) {	//����ǰһ��ʼ����
				info += super.attack(f, x, i);
		}
		return info;
	
	}
	
}
