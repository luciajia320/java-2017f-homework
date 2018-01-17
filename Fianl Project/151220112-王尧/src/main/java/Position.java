public class Position {
    private int x,y ;
    private boolean empty;

    private Creature holder;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;

        holder = null;
        empty = true;
    }


    private boolean isEmpty() {
        return empty;
    }

    public Creature getHolder() {
        return holder;
    }

    public void setHolder(Creature holder) {
        this.holder = holder;
        empty = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void clear() {
        holder = null;
        empty = true;
    }

}
