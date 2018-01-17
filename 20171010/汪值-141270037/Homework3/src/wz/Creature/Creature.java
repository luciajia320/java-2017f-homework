package wz.Creature;

import wz.position.*;

public interface Creature {
    public String getName();
    public void setPosition(Position position);
    public Position getPosition();
    public void report();
}
