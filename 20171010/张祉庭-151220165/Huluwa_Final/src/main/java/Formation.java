import java.util.*;
import java.util.Date;
import java.util.GregorianCalendar;

public class Formation
{
    public Position[][] positions;
    private Huluwa[] huluwas;
    private Grandpa grandpa=new Grandpa();
    private QueenSnake queen=new QueenSnake();
    public void set_map()
    {
        for(int i=4;i<12;i++)
            for(int j=0;j<13;j++)
            {
                positions[i][j].ClearHolder();
            }
        positions[0][6].setHolder(grandpa,0,6);
        positions[12][6].setHolder(queen,12,6);
        positions[3][3].setHolder(huluwas[0],3,3);
        positions[3][4].setHolder(huluwas[2],3,4);
        positions[3][5].setHolder(huluwas[6],3,5);
        positions[3][6].setHolder(huluwas[4],3,6);
        positions[3][7].setHolder(huluwas[3],3,7);
        positions[3][8].setHolder(huluwas[5],3,8);
        positions[3][9].setHolder(huluwas[1],3,9);
    }
    public void report_map()
    {
        for(int i=0;i<13;i++) {
            for (int j = 0; j < 13; j++) {
                if (positions[i][j].isHolderNull())
                    System.out.print("口口口口");
                else {
                    positions[i][j].ReturnHolder().report_status();
                }
            }
            System.out.print("\n");
        }
    }

    public void sort(){
        for(int i=0;i<7;i++)
            for(int j=3;j<10;j++)
            {if(positions[3][j].ReturnHolder() instanceof Huluwa && positions[3][j+1].ReturnHolder() instanceof Huluwa) {
                if (((Huluwa)positions[3][j].ReturnHolder()).seniority > ((Huluwa)positions[3][j + 1].ReturnHolder()).seniority) {
                    //     System.out.println(positions[j+1].holder.name+":"+(j+1)+"->"+j);
                    //     System.out.println(positions[j].holder.name+":"+j+"->"+(j+1));
                    Creature temp = positions[3][j].ReturnHolder();
                    positions[3][j].setHolder(positions[3][j + 1].ReturnHolder(),3,j);
                    positions[3][j + 1].setHolder(temp,3,j+1);
                }
            }
            }

    }

    public Formation(map m)
    {
        this.positions=m.positions;
        huluwas=new Huluwa[7];
        huluwas[0]=new Huluwa(COLOR.CHI);
        huluwas[1]=new Huluwa(COLOR.CHENG);
        huluwas[2]=new Huluwa(COLOR.HUANG);
        huluwas[3]=new Huluwa(COLOR.LV);
        huluwas[4]=new Huluwa(COLOR.QING);
        huluwas[5]=new Huluwa(COLOR.LAN);
        huluwas[6]=new Huluwa(COLOR.ZI);
  //      this.init_Huluwa();
        this.set_map();
        this.sort();
        //  this.report_map();
    }
}
