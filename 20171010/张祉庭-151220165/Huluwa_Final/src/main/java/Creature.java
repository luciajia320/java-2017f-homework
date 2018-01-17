import javax.annotation.Generated;
import javax.xml.ws.Action;
import java.lang.annotation.Target;
import java.util.*;
import java.util.Date;
import java.util.GregorianCalendar;
import java.awt.Image;
import java.net.URL;
import java.util.Random;
import javax.swing.ImageIcon;



class Location{
    int x;
    int y;
    public Location(int x,int y)
    {
        this.x=x;
        this.y=y;
    }

    public Location()
    {

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}

public class Creature{
    protected Image image;
    Location loc=new Location();
    boolean is_alive;
    private COLOR color;
    public COLOR getColor()
    {
        return color;
    }
    public void setColor(COLOR co)
    {
        color=co;
    }

    public Image getImage() {
        return this.image;
    }

    void report_status()
    {

    }

}

class QueenSnake extends Creature
{
    public QueenSnake()
    {
        ImageIcon iia=new ImageIcon("./src/main/resources/snack.png");
        image=iia.getImage();
    }
    public void report_status()
    {

        System.out.print("蛇 精   ");
    }
}

class Grandpa extends Creature
{
    public Grandpa()
    {
        ImageIcon iia=new ImageIcon("./src/main/resources/grandpa.png");
        image=iia.getImage();
    }
    public void report_status()
    {
        System.out.print("爷 爷   ");

    }
}

class Huluwa extends Creature{
    public String name;
    public int seniority;
    Huluwa(COLOR color)
    {
        this.setColor(color);
        switch(color)
        {
            case CHI:
            {
                name="老大";
                seniority=1;
                ImageIcon iia=new ImageIcon("./src/main/resources/CHI.png");
                image=iia.getImage();
            }
            break;
            case CHENG:
            {
                name="老二";
                seniority=2;
                ImageIcon iia=new ImageIcon("./src/main/resources/CHENG.png");
                image=iia.getImage();
            }
            break;
            case HUANG:
            {
                name="老三";
                seniority=3;
                ImageIcon iia=new ImageIcon("./src/main/resources/HUANG.png");
                image=iia.getImage();
            }
            break;
            case LV:
            {
                name="老四";
                seniority=4;
                ImageIcon iia=new ImageIcon("./src/main/resources/LV.png");
                image=iia.getImage();
            }
            break;
            case QING:
            {
                name="老五";
                seniority=5;
                ImageIcon iia=new ImageIcon("./src/main/resources/QING.png");
                image=iia.getImage();
            }
            break;
            case LAN:
            {
                name="老六";
                seniority=6;
                ImageIcon iia=new ImageIcon("./src/main/resources/LAN.png");
                image=iia.getImage();
            }
            break;
            case ZI:
            {
                name="老七";
                seniority=7;
                ImageIcon iia=new ImageIcon("./src/main/resources/zi.png");
                image=iia.getImage();
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
    Minoin()
    {
        this.setColor(COLOR.BLACK);
        ImageIcon iia=new ImageIcon("./src/main/resources/MINION.png");
        image=iia.getImage();
    }
}

enum COLOR{
    CHI,CHENG,HUANG,LV,QING,LAN,ZI,BLACK;
}