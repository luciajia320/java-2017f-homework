public class Position {
    private int x;
    private int y;
    private Creature holder;

    public Position(int x,int y){
        this.x=x;
        this.y=y;
    }

    public Creature getHolder(){
        return holder;
    }

    public void setHolder(Creature holder){
        this.holder=holder;
        holder.setPosition(this);
    }

    public int getX() {
        return x;
    }

    public int getY(){
        return y;
    }
}
