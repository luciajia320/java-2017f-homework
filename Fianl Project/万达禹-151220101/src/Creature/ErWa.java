package Creature;

import HuluTeam.BattleField;

public class ErWa extends HuluBoy{
	
	public ErWa(){
		super("����",600,100);
	}
	
	@Override
	public String skill(BattleField f,int x, int y) {
		String info = "";
		if(super.currentHP == 0) {
			info += ("�����������޷�����\r\n");
			return info;
		}
		info += super.attack(f, x, y);
		f.field[x*f.colnum+y].setVisible(true);
		f.field[(x+1)*f.colnum+y].setVisible(true);
		f.field[x*f.colnum+(y+1)].setVisible(true);
		f.field[(x+1)*f.colnum+(y+1)].setVisible(true);
		f.repaint();
		info += ("���޿�͸������\r\n");
		info += ("("+x+" , "+y+"), ");
		info += ("("+(x+1)+" , "+y+"), ");
		info += ("("+x+" , "+(y+1)+"), ");
		info += ("("+(x+1)+" , "+(y+1)+")");
		info += "\r\n\r\n";
		return info;
	}
}
