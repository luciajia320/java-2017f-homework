package Creature;

import HuluTeam.BattleField;

public class ErWa extends HuluBoy{
	
	public ErWa(){
		super("二娃",600,100);
	}
	
	@Override
	public String skill(BattleField f,int x, int y) {
		String info = "";
		if(super.currentHP == 0) {
			info += ("二娃已死亡无法攻击\r\n");
			return info;
		}
		info += super.attack(f, x, y);
		f.field[x*f.colnum+y].setVisible(true);
		f.field[(x+1)*f.colnum+y].setVisible(true);
		f.field[x*f.colnum+(y+1)].setVisible(true);
		f.field[(x+1)*f.colnum+(y+1)].setVisible(true);
		f.repaint();
		info += ("二娃看透了区域：\r\n");
		info += ("("+x+" , "+y+"), ");
		info += ("("+(x+1)+" , "+y+"), ");
		info += ("("+x+" , "+(y+1)+"), ");
		info += ("("+(x+1)+" , "+(y+1)+")");
		info += "\r\n\r\n";
		return info;
	}
}
