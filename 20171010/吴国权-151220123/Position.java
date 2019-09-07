public class Position {

    private int x;
    private int y;
    public boolean isempty;

    public Creature getHolder() {
        return holder;
    }

    public void setHolder(Creature holder) {
        this.holder = holder;
        this.isempty=false;
    }

    private Creature holder;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Position(int x,int y){
        super();
        this.x = x;
        this.y=y;
        this.isempty=true;
    }

    public void report(){
        if (!isempty){
            holder.report();
        }
        else{
            System.out.print("  ");
        }
    }
}