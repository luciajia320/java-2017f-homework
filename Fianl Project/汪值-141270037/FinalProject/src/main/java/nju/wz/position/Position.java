package nju.wz.position;

import nju.wz.creature.Creature;

public class Position {
    private int x;
    private int y;
    private Creature holder;
    private boolean hasCreature;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
        holder = null;
        hasCreature = false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setXY(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Creature getHolder() {
        if(hasCreature){
            return holder;
        }
        return null;
    }

    public void setHolder(Creature holder) {
        this.holder = holder;
        this.hasCreature = true;
    }

    public boolean isHasCreature() {
        return hasCreature;
    }

    public void reset(){
        holder = null;
        hasCreature = true;
    }
}
