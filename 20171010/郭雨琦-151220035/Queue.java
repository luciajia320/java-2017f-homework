import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;

public class Queue {


    private ArrayList<Position> positions;

    private ArrayList<Creature> creatures;


    public ArrayList<Position> getPositions() {
        return positions;
    }

    public ArrayList<Creature> getCreatures() {
        return creatures;
    }


    public Queue(ArrayList<Creature> brothers) {


        //this.positions = new Position[brothers.size()];

        this.creatures = new ArrayList<Creature>(brothers);
        this.positions = new ArrayList<Position>();
        /*for (int i = 0; i < brothers.size(); i++) {

            this.positions[i] = new Position(i,0);
            this.creatures[i].setPosition(this.positions[i]);
        }*/
        for (int i = 0; i < 7; i++){

            Position position = new Position(i,0);
            //position.setHolder(creatures.get(i));
            positions.add(position);

            this.creatures.get(i).setPosition(this.positions.get(i));
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
        for (int i = creatures.size() - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            Position position = creatures.get(index).getPosition();
            creatures.get(index).setPosition(creatures.get(i).getPosition());
            creatures.get(i).setPosition(position);
        }
    }

    public static void main(String[] args) {

        /*Huluwa[] brothers = new Huluwa[7];
        for (int i = 0; i < brothers.length; i++) {
            brothers[i] = new Huluwa(COLOR.values()[i], SENIORITY.values()[i]);
        }*/
        ArrayList<Creature> brothers = new ArrayList<Creature>();
        for (int i = 0; i < 7; i++) {
            Huluwa huluwa = new Huluwa(COLOR.values()[i], SENIORITY.values()[i],"\uD83D\uDC66");
            brothers.add(huluwa);
        }

        Grandpa grandpa=new Grandpa("\uD83D\uDC74");
        Snake snake = new Snake("\uD83D\uDC0D");
        Scorpion scorpion = new Scorpion("\uD83E\uDD82");
        /*Creature[] lackeys = new Lackey[6];
        for(int i = 0; i < lackeys.length; i++) {
            lackeys[i] = new Lackey();
        }*/
        ArrayList<Creature> lackeys = new ArrayList<Creature>();
        for (int i = 0; i < 6; i++) {
            lackeys.add(new Lackey("\uD83D\uDC38"));
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

