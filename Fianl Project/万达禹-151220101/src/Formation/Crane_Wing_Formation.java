package Formation;

import java.util.ArrayList;

import Creature.Creature;
import HuluTeam.BattleField;

public class Crane_Wing_Formation implements Formation{

	@Override
	public void SetFormation(BattleField a, ArrayList<Creature>b, int x, int y) {
		//System.out.println("∂‘’Ω£°∫◊“Ì’Û£∫");
		a.setHolder(b.get(0), x, y);
		int j=1;
		for(int i=1;i<=3;i++) {
			a.setHolder(b.get(j++), x+i, y+i);
			a.setHolder(b.get(j++), x-i, y+i);
		}
	}
	
}
