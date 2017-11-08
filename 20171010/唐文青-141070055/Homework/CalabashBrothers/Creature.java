package Homework.CalabashBrothers;
enum Species{
    Calabashbro, Grandpa, Scorpion, Snake, Underling, Flower
}
public class Creature implements Show {
    boolean isalive;
    boolean isgood;
    boolean gender;
    Species species;
    int x;
    int y;

    public void setposition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getx(){
        return x;
    }

    public int gety(){
        return y;
    }

    public Creature(){
        isalive = true;
        this.isgood = isgood;
        this.gender = gender;
        this.species = species;
    }

    public void showyourself(){
        System.out.print("?");
    }

}
