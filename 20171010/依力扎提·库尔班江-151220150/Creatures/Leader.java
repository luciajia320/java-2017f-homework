package Creatures;

import Creatures.Creature;

public class Leader extends Creature {
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
    public Leader(String name){this.name=name;}
}
