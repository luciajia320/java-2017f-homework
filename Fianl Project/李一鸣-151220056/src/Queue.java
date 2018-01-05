import java.util.ArrayList;

public class Queue {
    //  private Position[] positions;
    private ArrayList<Position>positions;
    public ArrayList<Position> getPositions() {
        return positions;
    }


    public Queue(int num) {
        this.positions=new ArrayList<Position>();
        for (int i = 0; i < num; i++)
            this.positions.add(new Position(0,i));
    }
    public void JoinIn(Creature creature){
        int index=0;
        while(positions.get(index).getHolder()!=null && index<positions.size())   //寻找一个空位
            index++;
        if (index!=positions.size()){
            positions.get(index).setHolder(creature);
        }
        return;
    }
}
