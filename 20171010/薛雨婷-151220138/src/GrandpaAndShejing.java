public class GrandpaAndShejing implements formation {
    private creature creature4;
    private creature creature5;

    public GrandpaAndShejing(grandpa grandp,shejing snake){
        this.creature4=grandp;
        this.creature5=snake;
    }
    @Override
    public void sort(Map queue) {
        Position[][] positions = queue.getPositions();
        positions[3][1].setHolder(this.creature5);
        positions[7][1].setHolder(this.creature4);
    }
}
