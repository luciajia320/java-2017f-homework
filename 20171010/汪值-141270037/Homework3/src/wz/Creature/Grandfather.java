package wz.Creature;

import wz.position.Position;

public class Grandfather implements Creature {
    private String name;
    private Position position;

    public Grandfather() {
        this.name = "爷";
        this.position = null;
    }

    @Override
    public String getName() {
        return this.name;
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
        System.out.print(this.name+" ");
    }
}
