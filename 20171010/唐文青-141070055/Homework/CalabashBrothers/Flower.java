package Homework.CalabashBrothers;

public class Flower extends Creature implements Show {
    public Flower() {
        isalive = true;
        isgood = true;
        gender = false;
        species = Species.Flower;
    }
    public void showyourself(){
        System.out.print("❀");
    }
}
