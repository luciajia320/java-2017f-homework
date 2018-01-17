
import com.sun.xml.internal.fastinfoset.util.CharArray;

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
    private ArrayDesigner arrayDesigner;
    private Huluwa[] brothers;
    public Queue(Huluwa[] brothers) {
        arrayDesigner = new ArrayDesigner();

        this.positions = new Position[brothers.length];

        this.creatures = brothers;
        this.brothers = brothers;
        for (int i = 0; i < brothers.length; i++) {

            this.positions[i] = new Position(new Coordinate(i+2,0));
            this.creatures[i].setPosition(this.positions[i]);
        }
    }

    public void insertCre(Creature obj){
        Creature temp [] = new Creature[this.creatures.length+1];
        int i;
        for (i = 0;i<this.creatures.length;i++)
            temp[i] = this.creatures[i];
        temp[i] = obj;
        this.creatures = temp;
    }

    public void designArray(String name){
        if(name == "鹤翼")
        {
            for(int i = 0;i<7;i++)
                insertCre(new Louluo());
            arrayDesigner.arrayDesignHeyi(creatures,7,new Coordinate(0,0),new Coordinate(0,3));
        }
        if(name == "衡轭")
        {
            //for(int i = 0;i<6;i++)
              //  insertCre(new Louluo());
            arrayDesigner.arrayDesignHenge(creatures,6,new Coordinate(0,0),new Coordinate(0,3));
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

    public void drawing(){
        char [][]drawing = new char[12][12];
        for (int i=0;i<12;i++)
            for (int j=0;j<12;j++)
                drawing[i][j] = ' ';
        for (int i=0;i<15;i++){
            drawing[creatures[i].getPosition().coordinate.getX()][creatures[i].getPosition().coordinate.getY()] = creatures[i].getCoin();
        }
        for (int i=0;i<12;i++){
            for (int j=0;j<12;j++) {
                if (drawing[i][j] != ' ')
                    System.out.print(drawing[i][j]);
                else
                    System.out.print("  ");
            }
            System.out.println("\n");
        }
        System.out.println("\n");
        System.out.flush();
    }

    private void shuffle() {
        Random rnd = ThreadLocalRandom.current();
        for (int i = 6; i > 0; i--) {
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


        Queue queue = new Queue(brothers);
        queue.insertCre(new Yeye());
        queue.insertCre(new Xiezijing());

        queue.rollCall();

        queue.shuffle();


        queue.rollCall();

        new InsertionSorter().sort(queue);
        queue.designArray("鹤翼");
        queue.rollCall();
        queue.drawing();
        queue.designArray("衡轭");
        queue.rollCall();
        queue.drawing();
    }
}
