import java.util.ArrayList;

public class HeyiFormation extends Formation {
	public HeyiFormation(ArrayList<Creature> creatures, int x, int y, int direction) {
		super(creatures, x, y, direction);
		creatures.get(0).setX(x);
		creatures.get(0).setY(y);
		final int spaceX = 70;
		final int spaceY = 50;
		for (int i = 1; i < creatures.size(); i++) {
			if (i % 2 == 1) {
				creatures.get(i).setX(x - direction * (i + 1) / 2 * spaceX);
				creatures.get(i).setY(y - (i + 1) / 2 * spaceY);
			} else {
				creatures.get(i).setX(x - direction * (i + 1) / 2 * spaceX);
				creatures.get(i).setY(y + (i + 1) / 2 * spaceY);
			}
		}
	}
}
