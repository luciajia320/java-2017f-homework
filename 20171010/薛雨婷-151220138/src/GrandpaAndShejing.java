import java.util.ArrayList;

public class GrandpaAndShejing implements layout {
    private creature creature4;
    private creature creature5;

    public GrandpaAndShejing(grandpa grandp,shejing snake){
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
