package Homework.CalabashBrothers.Creatures;
import Homework.CalabashBrothers.*;

public final class Underling extends Creature implements Show {
    void Underling(){
        isalive = true;
        isgood = false;
        gender = false;
        Species species = Species.UNDERLING;
    }
    @Override
    public void showyourself(){
        System.out.print("☻");
    }
}
