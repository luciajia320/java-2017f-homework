package Homework.CalabashBrothers.Creatures;
import Homework.CalabashBrothers.*;

public final class Scorpion extends Creature implements Show {
    void Scorpion(){
        isalive = true;
        isgood = false;
        gender = false;
        Species species = Species.SCORPION;
    }
    @Override
    public void showyourself(){
        System.out.print("蝎");
    }
}
