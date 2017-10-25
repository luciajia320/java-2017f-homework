
abstract public class Creature {

    protected PositionInterface position;
    protected String name;

    public String getName() {
        return name;
    }

    Creature(String name,PositionInterface position)
    {
        this.name=name;
        this.setPosition(position);
    }

    public void setPosition(PositionInterface position)
    {
        if(this.position!=null)
            this.position.setHolder(null);
        this.position=position;
        position.setHolder(this);
    }
    public PositionInterface getPosition(){
        return this.position;
    }

    public void report() {
        System.out.println(this.toString()+"@" + this.position.toString());
    }

    @Override
    public String toString() {
        return name;
    }

    abstract public void act();
}
