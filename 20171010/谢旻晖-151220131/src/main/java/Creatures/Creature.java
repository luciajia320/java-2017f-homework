package Creatures;

import Position.PositionInterface;

import java.awt.*;

abstract public class Creature {


    protected String mName;
    protected PositionInterface position;
    public String getName() {
        return mName;
    }

    protected Creature(String name, PositionInterface position)
    {
        this.mName=name;
        this.position=position;
        this.position.setHolder(this);
    }

    public void setPosition(PositionInterface position) {
        if (this.position != null)
            this.position.setHolder(null);// set the original position null
        this.position = position;
        position.setHolder(this);
    }
    public void report() {
        System.out.println(this.toString() + "@" + this.position.toString());
    }

    @Override
    public String toString() {
        return mName;
    }

    abstract public void act();
}
