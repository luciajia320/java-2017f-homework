import java.awt.geom.GeneralPath;
import java.text.Normalizer;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.List;

public class Space {
    private Position [][]positions;
    private Creature []creatures;
    private final static int N = 20;

    public Position[][] getPositions() {
        return positions;
    }

    public Creature[] getCreatures() {
        return creatures;
    }

    Space(){
        positions = new Position[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                positions[i][j] = new Position(i, j);
            }
        }
    }

    public void printPostion(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(positions[i][j].getHolder() == null)
                    System.out.print("  ");
                else positions[i][j].getHolder().reportName();
            }
            System.out.print("\n");
        }

        System.out.println("\n");
        System.out.flush();
    }


    public void setPositions(ArrayList<? extends Creature> brothers) {

        for (int i = 0; i < brothers.size(); i++) {
            int x = brothers.get(i).getPosition().getX();
            int y = brothers.get(i).getPosition().getY();
            positions[x][y].setHolder(brothers.get(i));
        }
    }

    public void setPosition(Creature creature){
        int x = creature.getPosition().getX();
        int y = creature.getPosition().getY();
        positions[x][y].setHolder(creature);
    }

    public static void main(String []args){
        ArrayList<Hulu> brothers = new ArrayList<Hulu>();
        for(int i = 6; i >= 0; i--) {
            Hulu b = new Hulu(Color.values()[i], Rank.values()[i]);
            brothers.add(b);
        }

        ArrayList<Minion> minions = new ArrayList<Minion>();
        for(int i = 0; i< 7;i++){
            Minion m = new Minion(i + 1);
            minions.add(m);
        }
        Grandpa grandpa = new Grandpa(0, 9);
        Snake snake = new Snake(0, 11);
        Scorpion scorpion = new Scorpion();

        Formation formation = new HEYI();
        Queue queue = new Queue(brothers);
        QueueMinion queueMinion = new QueueMinion(minions, formation, scorpion);
        Space space = new Space();
        space.setPositions(brothers);
        space.setPositions(minions);
        space.setPosition(grandpa);
        space.setPosition(scorpion);
        space.setPosition(snake);
        space.printPostion();

        Formation formation1 = new YANXING();
        QueueMinion queueMinion1 = new QueueMinion(minions, formation1, scorpion);
        Space space2 = new Space();
        space2.setPositions(brothers);
        space2.setPositions(minions);
        space2.setPosition(grandpa);
        space2.setPosition(scorpion);
        space2.setPosition(snake);
        space2.printPostion();
    }
}
