import java.util.*;
import java.util.Date;
import java.util.GregorianCalendar;


public class CraneWing{
    private Position[][] positions;

    public void set_map()
    {
        Minoin minion=new Minoin();
        positions[7][3].holder=minion;
        positions[7][9].holder=minion;
        positions[8][4].holder=minion;
        positions[8][8].holder=minion;
        positions[9][5].holder=minion;
        positions[9][7].holder=minion;
        positions[10][6].holder=minion;
    }



    CraneWing(map m)
    {
        this.positions=m.positions;
        this.set_map();
   //     this.report_map();

    }
}