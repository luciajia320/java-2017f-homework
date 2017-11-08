package Homework.CalabashBrothers;

public class Scorpion extends Creature implements Show {
    void Scorpion(){
        isalive = true;
        isgood = false;
        gender = false;
        Species species = Species.Scorpion;
    }
    public void showyourself(){
        System.out.print("蝎");
    }
}
