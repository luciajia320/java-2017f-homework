package Homework.CalabashBrothers;

public class Grandpa extends Creature implements Show {
    void Granpa() {
        isalive = true;
        isgood = true;
        gender = false;
        species = Species.Grandpa;
    }
    public void showyourself(){
        System.out.print("爷");
    }
}
