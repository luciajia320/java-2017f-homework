package Homework.CalabashBrothers;

public class Underling extends Creature implements Show {
    void Underling(){
        isalive = true;
        isgood = false;
        gender = false;
        Species species = Species.Underling;
    }
    public void showyourself(){
        System.out.print("☻");
    }
}
