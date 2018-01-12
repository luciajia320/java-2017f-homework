package HL.creature;
import  HL.Position;

public class Shejing implements Creature {
    private Position position;

    @Override
    public void report() {
        System.out.print("🐍");
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }
}
