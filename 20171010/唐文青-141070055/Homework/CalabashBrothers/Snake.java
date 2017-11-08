package Homework.CalabashBrothers;

public class Snake extends Creature implements Show {
    void Snake(){
        isalive = true;
        isgood = false;
        gender = true;
        Species species = Species.Snake;
    }
    public void showyourself(){
        System.out.print("蛇");
    }
}
