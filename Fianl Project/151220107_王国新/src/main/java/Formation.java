import java.util.ArrayList;

public class Formation {
	public static int left = 1;
	public static int right = -1;
	private ArrayList<Creature> creatures = new ArrayList<Creature>();

	public Formation(ArrayList<Creature> creatures, int x, int y, int direction) {
		this.creatures = creatures;
	}

	public ArrayList<Creature> getCreature() {
		return creatures;
	}
}
