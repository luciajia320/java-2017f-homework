package Creatures.Bad;

import Creatures.Bad.Monster;
import Position.PositionInterface;

public class Scorpion extends Monster {
    private static Scorpion ourInstance ;

    public static Scorpion getInstance(PositionInterface positionInterface) {
        if(ourInstance==null)
            ourInstance=new Scorpion(positionInterface);
        return ourInstance;
    }

    private Scorpion(PositionInterface positionInterface) {
        super("蝎",positionInterface);
    }


    @Override
    public void act(){
        System.out.println("我是蝎子精，我最爱蛇精");;
    }
}
