import javax.swing.*;
import java.util.*;
import java.util.Date;
import java.awt.*;
import java.util.GregorianCalendar;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;


public class map{
    int N =13;
    public Position[][] positions=new Position[N][N];

//    Graphics g;
    public void report_map()
    {
        for(int i=0;i<13;i++) {
            for (int j = 0; j < 13; j++) {
               if (positions[i][j].isHolderNull()){
                  System.out.print("| | | | |");
               }
                else {
                    positions[i][j].ReturnHolder().report_status();
    //                g.drawImage(positions[i][j].ReturnHolder().getImage(),i*20,j*20,this);

                }


            }
            System.out.print("\n");
        }
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("\n");
    }

    public map() {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                positions[i][j] = new Position();
            }
        }
    }



/*    public void buildmap()
    {
        g.setColor(new Color(250,240,170));
        g.fillRect(0,0,100,100);
        report_map();
    }
*/
 /*   public void paint(){
        buildmap();
    }
*/
}

class Position<T extends Creature>{
    private int index_x;
    private int index_y;
    private T holder;

    public void setHolder(T t,int x,int y)
    {
        index_x=x;
        index_y=y;
        holder=t;
        holder.loc.setX(index_x);
        holder.loc.setY(index_y);
    }
    public boolean isHolderNull()
    {
        if(holder==null)
            return true;
        else
            return false;
    }
    public T ReturnHolder()
    {
        return holder;
    }
    public void ClearHolder()
    {
        holder=null;
    }
}