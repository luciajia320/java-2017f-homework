public class Position {

    private Vector2 coordinate;

    public Creature getHolder() {
        return holder;
    }

    public void setHolder(Creature holder) {
        this.holder = holder;
    }

    private Creature holder;

    public int getX() {
        return coordinate.getX();
    }

    public void setX(int x) {
        this.coordinate.setX(x);
    }

    public Position(int x){
        super();
        this.coordinate = new Vector2(x, 0);
    }

    public Position(int x, int y){
        super();
        this.coordinate = new Vector2(x, y);
    }
}

