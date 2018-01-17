import java.util.ArrayList;

public class ScaleStrategy implements strategy {

	@Override
	public void ourStrategy(Map map, ArrayList<Creature> enemy, int x, int y) {
		System.out.println("This is Scale strategy.");
		map.setCreature(enemy.get(0), x, y);
		map.setCreature(enemy.get(1), x-1, y+1);
		map.setCreature(enemy.get(2), x-2, y+2);
		map.setCreature(enemy.get(3), x-3, y+3);
		map.setCreature(enemy.get(4), x, y+2);
		map.setCreature(enemy.get(5), x-1, y+3);
		map.setCreature(enemy.get(6), x, y+4);
		map.setCreature(enemy.get(7), x-1, y+5);
		map.setCreature(enemy.get(8), x, y+6);
		map.setCreature(enemy.get(9), x+1, y+3);
	}

}
