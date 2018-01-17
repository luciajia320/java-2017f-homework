import java.util.ArrayList;

public class WingStrategy implements strategy {

	@Override
	public void ourStrategy(Map map, ArrayList<Creature> enemy, int x, int y) {
		System.out.println("This is Wing strategy.");
		map.setCreature(enemy.get(0), x, y);
		int j=1;
		for(int i=1;i<=3;i++) {
			map.setCreature(enemy.get(j++), x+i, y+i);
			map.setCreature(enemy.get(j++), x-i, y+i);
		}
	}

}
