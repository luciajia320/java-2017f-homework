package HL;
import HL.creature.Creature;

public class Position {

    private Coordinate coorinate;
    private Creature holder;

    public Creature getHolder() {
        return holder;
    }

    public void setHolder(Creature holder) {
        this.holder = holder;
    }


    public Coordinate getCoorinate() {
        return coorinate;
    }

    public void setCoorinate(Coordinate coorinate) {
        this.coorinate = coorinate;
    }

    public Position(Coordinate x){
        super();
        this.coorinate = x;
    }

    public Position(int x, int y) {
        super();
        this.coorinate = new Coordinate(x, y);
    }
}
