import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Queue {
    private Position[] positions;
    private Creature[] creatures;

    public Queue(Creature[] creatures) {
        positions = new Position[creatures.length];
        this.creatures = creatures;
        for (int i = 0; i < creatures.length; i ++) {
            positions[i] = new Position();
            this.creatures[i].setPosition(positions[i]);
        }
    }

    public void shuffle() {
        Random rnd = ThreadLocalRandom.current();
        for (int i = creatures.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            Position position = creatures[index].getPosition();
            creatures[index].setPosition(creatures[i].getPosition());
            creatures[i].setPosition(position);
        }
    }

    public void rollCall() {
        for (Creature creature : creatures) {
            creature.report();
        }
        System.out.println();
        System.out.flush();

        for (Position position : positions) {
            position.getHolder().report();
        }

        System.out.println("\n");
        System.out.flush();
    }

    public Position[] getPositions() {
        return positions;
    }
}

