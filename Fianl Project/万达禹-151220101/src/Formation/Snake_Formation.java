package Formation;

import java.util.ArrayList;

import Creature.Creature;
import HuluTeam.BattleField;

public class Snake_Formation implements Formation{

	@Override
	public void SetFormation(BattleField a, ArrayList<Creature>b, int x, int y) {
		//System.out.println("��ս��������");
		a.setHolder(b.get(0), x, y);
		for(int i=1;i<4;i++) 
			a.setHolder(b.get(i), x-4+i, y );
		for(int i=1;i<4;i++) 
			a.setHolder(b.get(i+3),x+i,y);
		}
	}

