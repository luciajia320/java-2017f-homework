/*
* @author Minhui Xie
* @date 24,10,2017
* @time 18:35
*/
package View;
import Creatures.Creature;
import View.View;
import Position.PositionInterface;

public class PlainTextView implements View{
    private int GROUND_SIZE;
    private PositionInterface[] positionInterfaces;

    private static PlainTextView ourInstance;

    public static PlainTextView getInstance(int GROUND_SIZE,PositionInterface[] positionInterfaces) {
        if(ourInstance==null)
            ourInstance=new PlainTextView(GROUND_SIZE,positionInterfaces);
        return ourInstance;
    }

    private PlainTextView(int GROUND_SIZE,PositionInterface[] positionInterfaces)    {
        this.GROUND_SIZE=GROUND_SIZE;
        this.positionInterfaces=positionInterfaces;
    }

    public void show()
    {
        System.out.println("-----------------------------");
        for (int i = 0; i< GROUND_SIZE; i++) {
            for (int j = 0; j < GROUND_SIZE; j++) {
                Creature temp=positionInterfaces[GROUND_SIZE * i + j].getHolder();
                if(temp!=null)
                {
                    System.out.print(temp.getName());
                }
                else
                {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
        System.out.println("-----------------------------");
    }
}
