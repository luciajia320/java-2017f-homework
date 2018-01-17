package Creatures;

import Creatures.Creature;

public class CheeringCharacter extends Creature {
    String name;
    @Override
    public void speak() {
        System.out.print(name.toString());
    }

    public void cheer() {
        System.out.print("怼它！");
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public CheeringCharacter(String name){this.name=name;}
}
