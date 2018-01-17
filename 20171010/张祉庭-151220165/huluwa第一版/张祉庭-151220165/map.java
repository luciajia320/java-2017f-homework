import java.util.*;
import java.util.Date;
import java.util.GregorianCalendar;

public class map{
    int N =13;
    public Position[][] positions=new Position[N][N];

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
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("\n");
    }



    map() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                positions[i][j] = new Position();
            }
        }
    }

}

class Position{
    public int index_x;
    public int index_y;
    public Creature holder;
}