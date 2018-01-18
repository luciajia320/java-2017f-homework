package wz.Creature;

import wz.position.*;

public class Huluwa implements Creature, Comparable {
    private String name;
    private int ID;
    private String color;
    private Position position;

    public Huluwa(String name, String color, int ID) {
        this.name = name;
        this.ID = ID;
        this.color = color;
    }

    @Override
    public boolean compareTo(Comparable a) {
        if(a instanceof Huluwa){
            return this.getID() > ((Huluwa)a).getID();
        }
        return false;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getID() {
        return ID;

    }

    public String getColor() {
        return color;
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

    @Override
    public void report() {
        System.out.print(this.name+" ");
    }
}


