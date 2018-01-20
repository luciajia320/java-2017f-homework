package Homework.CalabashBrothers.Creatures;
import Homework.CalabashBrothers.*;

public final class Grandpa extends Creature implements Show {
    void Granpa() {
        isalive = true;
        isgood = true;
        gender = false;
        species = Species.GRANDPA;
    }
    @Override
    public void showyourself(){
        System.out.print("爷");
    }
}
