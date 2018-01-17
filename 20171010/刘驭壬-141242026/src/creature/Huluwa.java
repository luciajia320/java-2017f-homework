package creature;
import location.Location;
public class Huluwa extends Creature{
    private String color;
    private int rank;

    public Huluwa(String name, String color, int rank){
        this.name = name;
        this.color = color;
        this.rank = rank;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getColor() {
        return color;
    }

    public int getRank() {
        return rank;
    }
}
