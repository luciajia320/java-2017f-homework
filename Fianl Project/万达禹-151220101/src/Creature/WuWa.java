package Creature;

import HuluTeam.BattleField;

public class WuWa extends HuluBoy{

	public WuWa(){
		super("五娃",600,100);
	}
	
	@Override
	public String skill(BattleField f,int x, int y) {//攻击一整列的敌人
		String info="";
		if(super.currentHP == 0) {
			info += ("五已死亡无法攻击\r\n");
			return info;
		}
		info += ("五娃使用了大瀑布之术！攻击了：\r\n");
		for(int i=0;i<f.rownum;i++) {
				info += super.attack(f, i, y);
		}
		return info;
	
	}
	
}
