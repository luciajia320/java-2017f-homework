import java.util.*;
import java.util.Date;
import java.util.GregorianCalendar;


public class CraneWing extends Formation{
    //  private Position[][] positions;

    public void set_map()
    {
        super.set_map();
        Minoin minion=new Minoin();
        Minoin minion1=new Minoin();
        Minoin minion2=new Minoin();
        Minoin minion3=new Minoin();
        Minoin minion4=new Minoin();
        Minoin minion5=new Minoin();
        Minoin minion6=new Minoin();
        Minoin minion7=new Minoin();
        Minoin minion8=new Minoin();
        positions[7][3].setHolder(minion1,7,3);
        positions[7][9].setHolder(minion2,7,9);
        positions[8][4].setHolder(minion3,8,4);
        positions[8][8].setHolder(minion4,8,8);
        positions[9][5].setHolder(minion5,9,5);
        positions[9][7].setHolder(minion6,9,7);
        positions[10][6].setHolder(minion7,10,6);

    }



    CraneWing(map m)
    {
        super(m);
        //     this.positions=m.positions;
        this.set_map();
        this.sort();
        //     this.report_map();

    }
}