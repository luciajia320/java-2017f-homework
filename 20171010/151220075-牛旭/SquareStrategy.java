import java.util.ArrayList;

public class SquareStrategy implements strategy {

	@Override
	public void ourStrategy(Map map, ArrayList<Creature> enemy, int x, int y) {
		System.out.println("This is Square strategy.");
		map.setCreature(enemy.get(0), x, y);
		int j=1;
		for(int i=1;i<=enemy.size()/4;i++) {
			map.setCreature(enemy.get(j++), x+i, y+i);
			map.setCreature(enemy.get(j++), x-i, y+i);
		}
		for(int i=enemy.size()/4-1;i>0;i--) {
			map.setCreature(enemy.get(j++), x+i, y+enemy.size()/2-i);
			map.setCreature(enemy.get(j++), x-i, y+enemy.size()/2-i);
		}
		map.setCreature(enemy.get(enemy.size()-1), x, y+enemy.size()/2);
	}

}
