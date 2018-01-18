import java.util.ArrayList;

public class GooseStrategy implements strategy {

	@Override
	public void ourStrategy(Map map, ArrayList<Creature> enemy, int x, int y) {
		System.out.println("This is Goose strategy.");
		map.setCreature(enemy.get(0), x, y);
		for(int i=1;i<enemy.size();i++) {
			map.setCreature(enemy.get(i), x-i, y+i);
		}
	}

}
