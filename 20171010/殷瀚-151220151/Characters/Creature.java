package Characters;
import Position.Position;

public abstract class Creature {

    protected Position position;

    public abstract void report();

    public void setPosition(Position position) {
        this.position = position;
        position.setHolder(this);
    }

    public Position getPosition() {
        return position;
    }

    public void changePositionWith(Creature another){

        Creature creatureTemp = this;
        Position positionTemp = another.getPosition();
        another.setPosition(this.getPosition());
        creatureTemp.setPosition(positionTemp);
    }
}
