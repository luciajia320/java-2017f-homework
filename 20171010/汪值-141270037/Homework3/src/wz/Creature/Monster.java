package wz.Creature;

import wz.position.Position;

public abstract class Monster implements Creature{
    private String name;
    private Position position;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
        position.setHolder(this);
    }

    @Override
    public Position getPosition() {
        return position;
    }
}
