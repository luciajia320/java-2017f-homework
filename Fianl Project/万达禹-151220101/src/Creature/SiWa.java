package Creature;

import HuluTeam.BattleField;

public class SiWa extends HuluBoy{
	
	public SiWa(){
		super("四娃",600,100);
	}
	
	@Override
	public String skill(BattleField f,int x, int y) {//攻击一整行的敌人
		String info = "";
		if(super.currentHP == 0) {
			info += ("四娃已死亡无法攻击\r\n");
			return info;
		}
		info += ("四娃使用了豪火球之术！攻击了：\r\n");
		for(int i=super.position.GetPositionX()+1;i<f.colnum;i++) {	//从身前一格开始攻击
				info += super.attack(f, x, i);
		}
		return info;
	
	}
	
}
