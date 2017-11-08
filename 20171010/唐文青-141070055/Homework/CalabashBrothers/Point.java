package Homework.CalabashBrothers;

public class Point implements Show {
    public boolean isempty;
    int x;
    int y;
    Creature creature;
    Species species;

    public Point(int x, int y) {
        this.isempty = true;
        this.x = x;
        this.y = y;
        this.creature = new Flower();
        this.species = Species.Flower;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void putcreature(Creature creature, Species species){
        this.creature = creature;
        this.species = species;
        if(species != Species.Flower){
            this.isempty = false;
        }
    }

    public void showyourself() {
        this.creature.showyourself();
    }

}
