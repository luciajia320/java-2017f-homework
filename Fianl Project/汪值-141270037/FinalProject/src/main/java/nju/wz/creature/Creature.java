package nju.wz.creature;

import nju.wz.position.Position;

public interface Creature extends Comparable<Creature>{
    public void setPosition(Position position);
    public Position getPosition();
    public void report();
}
