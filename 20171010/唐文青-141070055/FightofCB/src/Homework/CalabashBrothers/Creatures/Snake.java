package Homework.CalabashBrothers.Creatures;
import Homework.CalabashBrothers.*;

public final class Snake extends Creature implements Show {
    void Snake(){
        isalive = true;
        isgood = false;
        gender = true;
        Species species = Species.SNAKE;
    }
    @Override
    public void showyourself(){
        System.out.print("蛇");
    }
}
