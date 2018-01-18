package hlw;

public class CreatureQueue {
    private Position[][] space;
    private Creature[] creatures;
    private int bound_x1;
    private int bound_x2;
    private int bound_y1;
    private int bound_y2;

    CreatureQueue(Creature[] creatures1, Position[][] sp, int bx1, int bx2, int by1, int by2){
        this.space = sp;
        creatures = creatures1;
        bound_x1 = bx1;
        bound_x2 = bx2;
        bound_y1 = by1;
        bound_y2 = by2;
    }

    public Creature[] get_creatures(){
        return creatures;
    }

    public Position[][] getSpace() {
        return space;
    }

    public int getBound_x1(){
        return bound_x1;
    }

    public int getBound_x2(){
        return bound_x2;
    }

    public int getBound_y1(){
        return bound_y1;
    }

    public int getBound_y2(){
        return bound_y2;
    }
}
