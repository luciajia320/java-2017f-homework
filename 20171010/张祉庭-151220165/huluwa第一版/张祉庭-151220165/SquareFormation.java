import java.util.*;
import java.util.Date;
import java.util.GregorianCalendar;


public class SquareFormation{
    private Position[][] positions;

    public void set_map()
    {
        Minoin minion=new Minoin();
        positions[5][6].holder=minion;
        positions[6][5].holder=minion;
        positions[6][7].holder=minion;
        positions[7][4].holder=minion;
        positions[7][8].holder=minion;
        positions[8][5].holder=minion;
        positions[8][7].holder=minion;
        positions[9][6].holder=minion;
    }



    SquareFormation(map m)
    {
        this.positions=m.positions;
        this.set_map();
//        this.report_map();

    }
}