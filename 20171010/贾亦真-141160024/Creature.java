package hlw;

public abstract class Creature {
    protected Position pos;
    Creature() {
        pos = null;
    }
    public boolean set_pos(Position p){
        if (pos != null){
            pos.clear_pos();
        }
        pos = p;
        p.put_in(this);
        return true;
    }
    public Position get_pos() {return pos;}
    public abstract String show();
    public abstract String get_symbol();
}
