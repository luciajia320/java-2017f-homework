package lsc.java.hulu_3;

public class Huluwa extends Creature {
    private int index;
    public Huluwa(int i)
    {
        this.index = i;
        this.spieces = 0;
    }
    public void appear(){
        switch (index)
        {
            case 1:
                System.out.print("大 ");
                break;
            case 2:
                System.out.print("二 ");
                break;
            case 3:
                System.out.print("三 ");
                break;
            case 4:
                System.out.print("四 ");
                break;
            case 5:
                System.out.print("五 ");
                break;
            case 6:
                System.out.print("六 ");
                break;
            case 7:
                System.out.print("七 ");
                break;
        }
    }
    public int return_index()
    {
        return index;
    }
}
