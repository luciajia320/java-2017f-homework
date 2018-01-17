package nju.wz.creature;

import nju.wz.position.Field;
import nju.wz.position.Position;

public abstract class Monster extends Player{

    private Position position;

    public Monster(Field field) {
        super(field);
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
