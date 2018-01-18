package lsc.java.hulu_3;

public class Snake extends Creature{
    public Snake()
    {
        this.spieces = 1;
    }
    public void appear(){
        System.out.print("蛇");
    }
    public int return_index()
    {
        return 1;
    }

}
