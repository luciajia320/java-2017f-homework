package Homework.CalabashBrothers.Creatures;
import Homework.CalabashBrothers.*;

public final class Flower extends Creature implements Show {
    public Flower() {
        isalive = true;
        isgood = true;
        gender = false;
        species = Species.FLOWER;
    }
    @Override
    public void showyourself(){
        System.out.print("❀");
    }
}
