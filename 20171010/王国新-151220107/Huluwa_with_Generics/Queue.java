import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;

public class Queue {
	private ArrayList<Creature> creatures;
	//说明Position里放的是Creature
	private ArrayList<Position<Creature>> positions;
	
	Queue(ArrayList<Creature> creatures){
		 this.creatures = creatures;
		 positions = new ArrayList<Position<Creature>>();
		 for (int i = 0; i < creatures.size(); i++) {
			 Position<Creature> position = new Position<Creature>();
			 position.setHolder(creatures.get(i));
			 positions.add(position);
		 }
	}

    public ArrayList<Creature> getCreatures() {
        return creatures;
    }
    
    public ArrayList<Position<Creature>> getPositions() {
        return positions;
    }
    
    public void report() {
        for (Position<Creature> position : this.positions) {
            position.getHolder().report();
        }
        System.out.println("\n");
        System.out.flush();
    }

    //打乱顺序
    public void shuffle() {
	        Random rnd = ThreadLocalRandom.current();
	        for (int i = creatures.size() - 1; i > 0; i--) {
	        int index = rnd.nextInt(i + 1);
	        Creature temp = positions.get(i).getHolder();
	        positions.get(i).setHolder(positions.get(index).getHolder());
	        positions.get(index).setHolder(temp);
        }
    }
}
