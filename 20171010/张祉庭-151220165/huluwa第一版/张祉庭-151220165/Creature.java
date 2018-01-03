import java.util.*;
import java.util.Date;
import java.util.GregorianCalendar;

public class Creature{
    boolean is_alive;
    void report_status()
    {

    }

}

class QueenSnake extends Creature
{
    public void report_status()
    {
        System.out.print("蛇 精   ");
    }
}

class Grandpa extends Creature
{
    public void report_status()
    {
        System.out.print("爷 爷   ");
    }
}

class Huluwa extends Creature{
    public String name;
    public COLOR color;
    public int seniority;
    Huluwa(COLOR color)
    {
        switch(color)
        {
            case CHI:
            {
                name="老大";
                seniority=1;
            }
            break;
            case CHENG:
            {
                name="老二";
                seniority=2;
            }
            break;
            case HUANG:
            {
                name="老三";
                seniority=3;
            }
            break;
            case LV:
            {
                name="老四";
                seniority=4;
            }
            break;
            case QING:
            {
                name="老五";
                seniority=5;
            }
            break;
            case LAN:
            {
                name="老六";
                seniority=6;
            }
            break;
            case ZI:
            {
                name="老七";
                seniority=7;
            }

        }
    }
    public void report_status()
    {
        if(seniority==1)
        {
            System.out.print("老 大   ");
        }
        else if(seniority==2)
        {
            System.out.print("老 二   ");
        }
        else if(seniority==3)
        {
            System.out.print("老 三   ");
        }
        else if(seniority==4)
        {
            System.out.print("老 四   ");
        }
        else if(seniority==5)
        {
            System.out.print("老 五   ");
        }
        else if(seniority==6)
        {
            System.out.print("老 六   ");
        }
        else if(seniority==7)
        {
            System.out.print("老 七   ");
        }
    }
}

class Minoin extends Creature{
    public String name;
    public void report_status()
    {
        System.out.print("小喽啰  ");
    }
}

enum COLOR{
    CHI,CHENG,HUANG,LV,QING,LAN,ZI;
}