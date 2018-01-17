import java.util.*;
import java.util.Date;
import java.util.GregorianCalendar;


public class Flying extends Formation{
    //  private Position[][] positions;

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
        positions[5][3].setHolder(minion1,5,3);
        positions[6][4].setHolder(minion2,6,4);
        positions[7][5].setHolder(minion3,7,5);
        positions[8][6].setHolder(minion4,8,6);
        positions[9][7].setHolder(minion5,9,7);
        positions[10][8].setHolder(minion6,10,8);
        positions[11][9].setHolder(minion7,11,9);
    }


    Flying(map m)
    {
        super(m);

        this.set_map();
        this.sort();
        //      this.report_map();

    }
}