public abstract class Creature {
    public abstract void report();

    protected Position position;
    public void setPosition(Position position) {
        this.position = position;
        position.setHolder(this);
    }
    public Position getPosition() {
        return position;
    }
}
