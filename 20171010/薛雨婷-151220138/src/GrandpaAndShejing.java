import java.util.ArrayList;

public class GrandpaAndShejing <T extends creature> implements layout  {
    private T creature4;
    private T creature5;

    public GrandpaAndShejing(T grandp,T snake){
        this.creature4=grandp;
        this.creature5=snake;
    }
    @Override
    public void sort(Map queue) {
        ArrayList<ArrayList<Position>> positions= queue.getPositions();
        positions.get(3).get(1).setHolder(this.creature5);
        positions.get(5).get(1).setHolder(this.creature4);
    }
}
