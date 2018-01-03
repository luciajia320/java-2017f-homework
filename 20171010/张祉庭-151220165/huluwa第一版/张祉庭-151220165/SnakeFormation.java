import java.util.*;
import java.util.Date;
import java.util.GregorianCalendar;


public class SnakeFormation{
    private Position[][] positions;
    private Huluwa[] huluwas;
    private Grandpa grandpa=new Grandpa();
    private QueenSnake queen=new QueenSnake();
    public void set_map(){
        positions[0][6].holder=grandpa;
        positions[12][6].holder=queen;
        positions[3][3].holder=huluwas[0];
        positions[3][4].holder=huluwas[2];
        positions[3][5].holder = huluwas[6];
        positions[3][6].holder = huluwas[4];
        positions[3][7].holder = huluwas[3];
        positions[3][8].holder = huluwas[5];
        positions[3][9].holder = huluwas[1];
    }



    public void sort(){
        for(int i=0;i<7;i++)
            for(int j=3;j<10;j++)
            {if(positions[3][j].holder instanceof Huluwa && positions[3][j+1].holder instanceof Huluwa) {
                if (((Huluwa)positions[3][j].holder).seniority > ((Huluwa)positions[3][j + 1].holder).seniority) {
                    //     System.out.println(positions[j+1].holder.name+":"+(j+1)+"->"+j);
                    //     System.out.println(positions[j].holder.name+":"+j+"->"+(j+1));
                    Creature temp = positions[3][j].holder;
                    positions[3][j].holder = positions[3][j + 1].holder;
                    positions[3][j + 1].holder = temp;
                }
            }
            }

    }
    SnakeFormation(map m,Huluwa[] h)
    {
        this.positions = m.positions;
        this.huluwas = h;
        this.set_map();
        this.sort();
 //       this.report_map();
    }
}