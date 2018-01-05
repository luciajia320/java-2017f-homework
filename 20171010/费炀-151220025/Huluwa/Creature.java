
public class Creature {
    protected Position position;

    public Creature getCreature() {
        return this;
    }

    public void report() {
        System.out.print(this);
    }

    public void setPosition(Position position) {
        this.position = position;
        position.setHolder(this);
    }


    public Position getPosition() {
        return position;
    }
}
