import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Queue {


    private Position[] positions;

    public Position[] getPositions() {
        return positions;
    }


    public Creature[] getCreatures() {
        return creatures;
    }



    private Creature[] creatures;

    public Queue(Huluwa[] brothers) {


        this.positions = new Position[brothers.length];

        this.creatures = brothers;

        for (int i = 0; i < brothers.length; i++) {

            this.positions[i] = new Position(i,0);
            this.creatures[i].setPosition(this.positions[i]);
        }
    }


    public void rollCall() {
        for (Creature creature : this.creatures) {
            creature.report();
        }
        System.out.println();
        System.out.flush();

        for (Position position : this.positions) {

            position.getHolder().report();
        }

        System.out.println("\n");
        System.out.flush();
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

    public static void main(String[] args) {

        Huluwa[] brothers = new Huluwa[7];
        for (int i = 0; i < brothers.length; i++) {
            brothers[i] = new Huluwa(COLOR.values()[i], SENIORITY.values()[i]);
        }
        Grandpa grandpa=new Grandpa();
        Snake snake = new Snake();
        Scorpion scorpion = new Scorpion();
        Creature[] lackeys = new Lackey[6];
        for(int i = 0; i < lackeys.length; i++) {
            lackeys[i] = new Lackey();
        }

        Queue queue = new Queue(brothers);

      //  queue.rollCall();

        queue.shuffle();


        //queue.rollCall();

       // new InsertionSorter().sort(queue);

       // queue.rollCall();

       // queue.shuffle();


       // queue.rollCall();

        new BubbleSorter().sort(queue);
        Space space = new Space();
        space.addPositiveCreatures(brothers);
        space.addNegativeCreatures(scorpion,lackeys);
        space.addOnlookerCreatures(grandpa, snake);

        space.setPositive();
        space.setNegative();
        space.setOnlooker();
        space.shout();
        space.print();
        System.out.print("--------------------------------------\n");
        System.out.print("敌人变换阵法\n");
        System.out.print("--------------------------------------\n");
        space.changeNegative(FORMATIONS.YANXING);
        space.shout();
        space.print();
    }
}

