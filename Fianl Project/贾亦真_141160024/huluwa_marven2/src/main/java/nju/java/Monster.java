package nju.java;

import java.util.ArrayList;

public abstract class Monster extends Creature {

    public Monster(int x, int y, Field field){
        super(x, y, field);
    }

    @Override
    public ArrayList< Human > getEnemies() {
        ArrayList<Human> enemies = this.field.getHumans();
        return enemies;
    }

}
