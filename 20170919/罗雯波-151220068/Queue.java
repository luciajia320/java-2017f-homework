import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Queue {
    private Position[] positions;
    private Creature[] creatures;
    private Embattler embattler = new Embattler();
    private Sorter sorter = new Sorter();

    public Queue(Creature[] creatures) {
        positions = new Position[creatures.length];
        this.creatures = creatures;
        for (int i = 0; i < creatures.length; i ++) {
            positions[i] = new Position();
            this.creatures[i].setPosition(positions[i]);
        }
    }
    public void embattle(FORMATION formation, PosCoord leadPosCoord,
                         ORIENTATION orientation) {
        embattler.embattle(positions, formation, leadPosCoord, orientation);
    }
    public Queue(Creature[] creatures, FORMATION formation,
                 PosCoord leadPosCoord, ORIENTATION orientation) {
        this(creatures);
        embattle(formation, leadPosCoord, orientation);
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

    public void sort(SortType sortType) {
        sorter.sort(positions, sortType);
    }
}

