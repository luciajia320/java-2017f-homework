package wz.Creature;

import wz.position.Position;

public class Vacancy implements Creature {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setPosition(Position position) {

    }

    @Override
    public Position getPosition() {
        return null;
    }

    @Override
    public void report() {
        System.out.print("口 ");
    }
}
