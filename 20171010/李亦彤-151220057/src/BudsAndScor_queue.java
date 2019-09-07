import java.util.ArrayList;
//小喽啰，蝎子精阵型
public class BudsAndScor_queue extends Queue{
    private int begin;

    //阵型1
    public BudsAndScor_queue(ArrayList<Buddy> buddies, Scorpion scor) {
        this.positions = new ArrayList<Position<Creature>>();
        this.creatures = new ArrayList<Creature>();
        begin=(N-buddies.size())/2;
        this.creatures.add(scor);
        for(int i=1;i<buddies.size()+1;i++)
            this.creatures.add(buddies.get(i-1));
        for (int i = 0; i <creatures.size(); i++) {

            this.positions.add(new Position(i+begin,N/2-i));
            this.creatures.get(i).setPosition(this.positions.get(i));
        }
    }

    //阵型2
    public void Heyi()
    {
        this.positions = new ArrayList<Position<Creature>>();
        int i;
        for (i = 0; i < creatures.size()/2+1; i++) {

            this.positions.add(new Position(i+begin,N/2-i));
            this.creatures.get(i).setPosition(this.positions.get(i));
        }
        for (int j = 0; j+i < creatures.size(); j++) {

            this.positions.add(new Position(i-j-2+begin,N/2-i-j));
            this.creatures.get(j+i).setPosition(this.positions.get(i+j));
        }
    }
}
