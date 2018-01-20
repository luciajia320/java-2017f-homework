import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Queue extends Formation{

    public Position[] getPositions() {
        return positions;
    }


    public Creature[] getCreatures() {
        return creatures;
    }




    Queue(int a, int b, int w, int h, Creature[] c, Creature l) {


        super(a, b, w, h, c, l);

    }


    private void shuffle() {
        Random rnd = ThreadLocalRandom.current();
        for (int i = creatures.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            Position position = creatures[index].getPosition();
            creatures[index].setPosition(creatures[i].getPosition());
            creatures[i].setPosition(position);
        }
    }


    @Override
    public void arrange() {
        for (int i = 0; i < creatures.length; i++) {

            this.positions[i] = new Position(i, 0);
            this.creatures[i].setPosition(this.positions[i]);
        }
        shuffle();
        new BubbleSorter().sort(this);
        lp = new Position(2, 3);
        leader.setPosition(lp);
    }
}

