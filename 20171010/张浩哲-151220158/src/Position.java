public class Position {
    public Coordinate coordinate;
    public Creature getHolder() {
        return holder;
    }

    public void setHolder(Creature holder) {
        this.holder = holder;
    }

    private Creature holder;


    public Position(Coordinate coordinate){
        this.coordinate = coordinate;
    }
}
