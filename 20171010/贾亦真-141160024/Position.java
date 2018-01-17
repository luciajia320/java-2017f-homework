package hlw;

public class Position {
    private boolean avail;
    private Creature holder;
    private Coord coord;
    Position(Coord cd){
        coord = cd;
        avail = true;
        holder = null;
    }
    public Creature get_holder(){
        return holder;
    }
    public boolean get_avail(){
        return avail;
    }
    public Coord get_coord() {
        return coord;
    }
    public void put_in(Creature creature){
        avail = false;
        holder = creature;
    }
    public void clear_pos(){
        avail = true;
        holder = null;
    }

    public String show(){
        if (avail){
            return coord.toString() + " " + "avail:" + avail + " ";
        }
        else{
            return coord.toString() + " " + "avail:" + avail + " holder:" + holder.show();
        }
    }
}
