public class Position <T extends Creature>{

    private int x;
    private int y;
    public T getHolder() {
        return holder;
    }

    public void setHolder(T holder) {
        this.holder = holder;
    }

    private T holder;

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public Position()
    {
        this.x=-1;
        this.y=-1;
    }
    public Position(int x,int y){
        super();
        this.x = x;
        this.y=y;
    }
}
