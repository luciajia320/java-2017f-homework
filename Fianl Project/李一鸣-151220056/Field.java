import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Field {
    final int N=15;
    static final int HLW_SUM=7;

  //  private Position[][] positions;
    private ArrayList<ArrayList<Position>>positions;
    private ArrayList<ArrayList<Creature>> creatures;

    public ArrayList<Position> getPositions(int line) {
       // return positions.get(i);
        ArrayList<Position>tmpp=new ArrayList<>();
        for (int i=(N-HLW_SUM)/2;i<(N+HLW_SUM)/2;++i)
            tmpp.add( positions.get(i).get(line));
        return tmpp;
    }

    public ArrayList<ArrayList<Position>> getPositions(){
        return positions;
    }
    public ArrayList<ArrayList<Creature>> getCreatures() {
        return creatures;
    }

    public Field (){
        this.positions = new ArrayList<ArrayList<Position>>();
        this.creatures = new ArrayList<ArrayList<Creature>>();
        for(int i=0;i<N;++i) {
            this.positions.add(new ArrayList<Position>());
            this.creatures.add(new ArrayList<Creature>());
        }
        for (int i = 0; i < N ;++i)
            for (int j = 0 ; j< N ;++j) {
                this.creatures.get(i).add(new Blank());
                this.positions.get(i).add(new Position(i,j));/*[i][j]*/
                this.creatures.get(i).get(j).setPosition(this.positions.get(i).get(j));
            }
    }

    public void putIn(Queue queue){
        ArrayList<Position>tmp1 = queue.getPositions();
        for (int i=0 ; i<tmp1.size() ; ++i){
            int x= tmp1.get(i).getX();
            int y= tmp1.get(i).getY();
            this.positions.get(x).get(y).setHolder(tmp1.get(i).getHolder());
            this.creatures.get(x).set(y,tmp1.get(i).getHolder());
            this.creatures.get(x).get(y).setPosition(tmp1.get(i));
        }
    }
    public void clean(){
        for (int i=0;i<N;++i)
            for (int j=0 ;j<N ;++j){
                //creatures[i][j]=new Blank();
                creatures.get(i).add(new Blank());
                positions.get(i).get(j).setHolder(new Blank());
            }
    }
    public void rollCall() {
        for (int i=0;i<N;++i) {
            for (int j = 0; j < N; ++j)
                this.positions.get(i).get(j).getHolder().report();
            System.out.println();
        }
        System.out.flush();
    }

    public void shuffle() {
        Random rnd = ThreadLocalRandom.current();
        for (int i =  HLW_SUM-1; i > 0; i--) {
            int index = rnd.nextInt(i + 1)+4;   //4是 7个葫芦娃处于15个位置正中间左右相等 4-10

            Position position = creatures.get(index).get(1).getPosition();
            Creature creature = creatures.get(index).get(1);

            positions.get(index).get(1).setHolder(creatures.get(i+4).get(1));
            positions.get(i+4).get(1).setHolder(creature);
            positions.get(index).get(1).setY(i+4);
            positions.get(i+4).get(1).setY(index);

            creatures.get(index).set(1, creatures.get(i+4).get(1));
            creatures.get(i+4).set(1, creature);
        }
    }


}

