import java.util.ArrayList;
//葫芦娃阵型
public class Huluwa_queue extends Queue{
    private int begin;
    public Huluwa_queue(ArrayList<Huluwa> brothers) {
        begin=(N-brothers.size())/2;
        this.positions = new ArrayList<>();
        this.creatures = new ArrayList<>();
        for(int i=0;i<brothers.size();i++)
            this.creatures.add(brothers.get(i));

        for (int i = 0; i < brothers.size(); i++) {
            this.positions.add( new Position(i+begin,N/2+N/4));
            Position<Creature> p = this.positions.get(i);
            this.creatures.get(i).setPosition(p);
        }
    }
}
