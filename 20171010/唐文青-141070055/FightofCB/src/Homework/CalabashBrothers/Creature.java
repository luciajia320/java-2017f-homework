package Homework.CalabashBrothers;
import Homework.CalabashBrothers.*;

public abstract class Creature implements Show {
    public boolean isalive;
    public boolean isgood;
    public boolean gender;
    public Species species;
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

    public abstract void showyourself();

}
