package main.java.nju.java;

public class Position {

    private int x,y;
    private boolean occupied;
    private  boolean border;

    public boolean isOccupied() {   return occupied; }

    public Creature getHolder() {
        return holder;
    }

    public void setHolder(Creature holder) {
        this.holder = holder;
        occupied = true;
    }

    private Creature holder;

    public int getX() {
        return x;
    }

    public int getY() { return y; }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void clear(){
        holder = null;
        occupied = false;
    }

    public Position(int x, int y){
        super();
        holder = null;
        this.x = x;
        this.y = y;
        occupied = false;
        border = false;
    }

    public void setBorder(){
        border = true;
    }

    public boolean isBorder(){
        return this.border;
    }
}
