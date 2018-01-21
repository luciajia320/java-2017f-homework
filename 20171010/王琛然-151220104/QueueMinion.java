import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class QueueMinion {
    final int N = 20;

    private Position[] positions;
    private Creature[] creatures;

    public Position[] getPositions() {
        return positions;
    }

    public Creature[] getCreatures() {
        return creatures;
    }

    public QueueMinion(ArrayList<? extends Creature> minions, Formation formation, Creature scorpion){

        formation.excuete(minions, scorpion);

    }

    public void rollCall(){
        System.out.println("小喽啰报告位置：");
        for(Creature creature: this.creatures){
            creature.report();
        }
        System.out.println("\n");
        System.out.flush();

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
}
