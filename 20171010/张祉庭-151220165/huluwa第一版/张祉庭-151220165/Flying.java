import java.util.*;
import java.util.Date;
import java.util.GregorianCalendar;


public class Flying{
    private Position[][] positions;

    public void set_map()
    {
        Minoin minion=new Minoin();
        positions[5][3].holder=minion;
        positions[6][4].holder=minion;
        positions[7][5].holder=minion;
        positions[8][6].holder=minion;
        positions[9][7].holder=minion;
        positions[10][8].holder=minion;
        positions[11][9].holder=minion;
    }


    Flying(map m)
    {
        this.positions=m.positions;
        this.set_map();
  //      this.report_map();

    }
}