package lsc.java.hulu_3;

public class Scorpion extends  Creature{
    public Scorpion()
    {
        this.spieces = 2;
    }
    public void appear(){
        System.out.print("蝎");
    }
    public int return_index()
    {
        return 1;
    }

}
