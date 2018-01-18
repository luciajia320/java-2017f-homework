package nju.wz.creature;

import nju.wz.position.Position;

public class Vacancy implements Creature {


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

    @Override
    public int compareTo(Creature o) {
        return 0;
    }
}
