package nju.java;

import java.util.ArrayList;

public abstract class Human extends Creature {

    Human(int x, int y, Field field){
        super(x, y, field);
    }

    @Override
    public ArrayList<Monster> getEnemies() {
        ArrayList<Monster> enemies = this.field.getMonsters();
        return enemies;
    }


}
