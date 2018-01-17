import java.util.*;
import java.util.Date;
import java.util.GregorianCalendar;

public class FormationClear
{
    private Position[][] positions;

    public void set_map()
    {
        for(int i=4;i<12;i++)
            for(int j=0;j<13;j++)
            {
                positions[i][j].holder=null;
            }
    }
    public void report_map()
    {
        for(int i=0;i<13;i++) {
            for (int j = 0; j < 13; j++) {
                if (positions[i][j].holder == null)
                    System.out.print("口口口口");
                else {
                    positions[i][j].holder.report_status();
                }
            }
            System.out.print("\n");
        }
    }

    FormationClear(map m)
    {
        this.positions=m.positions;
        this.set_map();
      //  this.report_map();
    }
}
