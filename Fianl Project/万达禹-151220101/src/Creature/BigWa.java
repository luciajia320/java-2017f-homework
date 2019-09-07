package Creature;

import HuluTeam.BattleField;

public class BigWa extends HuluBoy{
	
	public BigWa(){
		super("����",600,150);
	}
	
	@Override
	public String skill(BattleField f,int x, int y) {
		String info = "";
		if(super.currentHP == 0) {
			info += ("�����������޷�����\r\n");
			return info;
		}
		info += ("���������AOE�������ˣ�\r\n");
		info += super.attack(f, x, y);
		info += super.attack(f, x+1, y+1);
		info += super.attack(f, x+1, y);
		info += super.attack(f, x, y+1);
		info += "\n";
		return info;
//		super.attack(f, f.field[(y+1)*f.colnum+(x+1)].GetHolder());
//		super.attack(f, f.field[(y+1)*f.colnum+(x+1)].GetHolder());
//		super.attack(f, f.field[(y+1)*f.colnum+(x+1)].GetHolder());
//		super.attack(f, f.field[(y+1)*f.colnum+(x+1)].GetHolder());
	}
	
}
