import java.util.ArrayList;

public class ArrowStrategy implements strategy {

	@Override
	public void ourStrategy(Map map, ArrayList<Creature> enemy, int x, int y) {
		System.out.println("This is Arrow formation.");
		int i;
		for(i=0;i<enemy.size()/2;i++) {
			map.setCreature(enemy.get(i), x, y+i);
		}
		int j=i;
		for(i=1;j<enemy.size();i++) {
			map.setCreature(enemy.get(j++), x+i, y+i);
			map.setCreature(enemy.get(j++), x-i, y+i);
		}
	}

}
