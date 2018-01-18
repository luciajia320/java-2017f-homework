import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;

public class Queue {
    final static int N=16;
    //protected Position[] positions;
    protected ArrayList<Position<Creature>> positions;
    public ArrayList<Position<Creature>> getPositions() {
        return positions;
    }

    protected  ArrayList<Creature> creatures;

    public ArrayList<Creature> getCreatures() {
        return creatures;
    }

    public Queue(){};
    public void rollCall() {
        for (Position position : this.positions) {

            position.getHolder().report();
        }

        System.out.println("\n");
        //System.out.flush();
    }

    public void shuffle() {
        Random rnd = ThreadLocalRandom.current();
        for (int i = creatures.size() - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            Position<Creature> position = creatures.get(index).getPosition();
            creatures.get(index).setPosition(creatures.get(i).getPosition());
            creatures.get(i).setPosition(position);
        }
    }


}

