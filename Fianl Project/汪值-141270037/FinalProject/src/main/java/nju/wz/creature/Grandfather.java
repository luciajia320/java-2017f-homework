package nju.wz.creature;

import nju.wz.position.Field;
import nju.wz.position.Position;

public class Grandfather extends GoodPerson implements Creature {
    private Position position;

    public Grandfather(Field field, int id) {
        super(field);
        setName("爷");
        setID(id);
        setFightPower(3.0);
        setImageName("yeye.jpg");
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
        position.setHolder(this);
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public void report() {
        System.out.print(getName() + " ");
    }

}
