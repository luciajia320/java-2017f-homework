import java.util.*;
import java.util.Date;
import java.util.GregorianCalendar;


public class SquareFormation extends Formation{

    public void set_map()
    {
        super.set_map();
        Minoin minion1=new Minoin();
        Minoin minion2=new Minoin();
        Minoin minion3=new Minoin();
        Minoin minion4=new Minoin();
        Minoin minion5=new Minoin();
        Minoin minion6=new Minoin();
        Minoin minion7=new Minoin();
        Minoin minion8=new Minoin();
  //      Minoin minion9=new Minoin();
        positions[5][6].setHolder(minion1,5,6);
        positions[6][5].setHolder(minion2,6,5);
        positions[6][7].setHolder(minion3,6,7);
        positions[7][4].setHolder(minion4,7,4);
        positions[7][8].setHolder(minion5,7,8);
        positions[8][5].setHolder(minion6,8,5);
        positions[8][7].setHolder(minion7,8,7);
        positions[9][6].setHolder(minion8,9,6);


    }

    public SquareFormation(map m)
    {
        super(m);

        this.set_map();
        this.sort();
    }

}