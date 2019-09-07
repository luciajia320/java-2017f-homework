package Formation;

import java.util.ArrayList;

import Creature.Creature;
import HuluTeam.BattleField;

public class Arrow_Formation implements Formation{

	@Override
	public void SetFormation(BattleField a, ArrayList<Creature>b, int x, int y) {
		//System.out.println("��ս����ʸ��");
		int i=0;
		for(i=0;i<b.size()/2;i++) {
			a.setHolder(b.get(i), x, y+i);
		}
		int j=i;
		for(i=1;j<b.size();i++) {
			a.setHolder(b.get(j++), x+i, y+i);
			a.setHolder(b.get(j++), x-i, y+i);
		}
	}

}
