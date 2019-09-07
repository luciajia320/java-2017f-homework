import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.List;

public class Queue {
    final int N = 20;

    private Position[] positions;
    private ArrayList<? extends Creature> creatures;

    public Position[] getPositions() {
        return positions;
    }

    public ArrayList<? extends Creature> getCreatures() {
        return creatures;
    }

    public Queue(ArrayList<? extends Creature> list){
        this.creatures = list;
        this.positions = new Position[creatures.size()];
        for(int i =0; i < creatures.size(); i++){
           this.positions[i] = new Position(i + 5, 5);
           this.creatures.get(i).setPosition(this.positions[i]);
        }
        new BubbleSort().sort(this);
    }

    public void rollCall(){
        System.out.println("葫芦娃报告位置：");
        for(Creature creature: this.creatures){
            creature.report();
        }
        System.out.println("\n");
        System.out.flush();

    }

    public void shuffle() {
        Random rnd = ThreadLocalRandom.current();
        for (int i = creatures.size() - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            Position position = creatures.get(index).getPosition();
            creatures.get(index).setPosition(creatures.get(i).getPosition());
            creatures.get(i).setPosition(position);
        }
    }

    /*
    public static void main(String[] args){
        Hulu[] brothers = new Hulu[7];
        for(int i = 0; i< brothers.length; i++){
            brothers[i] = new Hulu(Color.values()[i], Rank.values()[i]);
        }

        Queue queue = new Queue(brothers);
        queue.rollCall();
        queue.shuffle();
        queue.rollCall();

        System.out.println("InsertionSort");
        new InsertionSort().sort(queue);
        queue.rollCall();
        queue.shuffle();
        queue.rollCall();

        System.out.println("BubbleSort");
        new BubbleSort().sort(queue);
        queue.rollCall();
    }
    */
}
