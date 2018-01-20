/*
* @author Minhui Xie
* @date 24,10,2017
* @time 18:35
*/
package View;
import BattleGround.TwoDimensionBattleGround;
import Creatures.Creature;
import View.View;
import Position.PositionInterface;

public class PlainTextView implements View{
    private int GROUND_SIZE= TwoDimensionBattleGround.GROUND_SIZE;

    private static PlainTextView ourInstance;

    public static PlainTextView getInstance() {
        if(ourInstance==null)
            ourInstance=new PlainTextView();
        return ourInstance;
    }

    private PlainTextView()    {

    }

    public void show(PositionInterface[] positionInterfaces)
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
