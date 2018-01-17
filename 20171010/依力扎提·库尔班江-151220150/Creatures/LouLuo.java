package Creatures;

import Creatures.Creature;

public class LouLuo extends Creature {
    String name;
    @Override
    public void speak() {
        System.out.print(name.toString());
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public LouLuo(String name){this.name=name;}
}
