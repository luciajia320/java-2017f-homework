public class Position {
    private Creature holder;

    public void SetHolder(Creature holder){this.holder = holder;}
    public Creature GetHolder(){return holder;}

    public Position(){
        super();
        holder = null;
    }
}
