package battlefield;
import mainroles.*;

import java.awt.*;


public class Position {
    private int x;
    private int y;
    public static Image gs = null;

    private Creature holder;
    Position()
    {
        holder = null;

    }

    public synchronized boolean setHolder(Creature c)
    {
        if(holder!=null)
            return false;
        this.holder = c;
        c.setPosition(this);
        return true;
    }
    public synchronized void clearHolder()
    {
        this.holder = null;
    }
    public  Creature getHolder()
    {
        return holder;
    }

    public synchronized int getX()
    {
        return x;
    }
    public synchronized int getY()
    {
        return y;
    }
    public synchronized void setX(int x){this.x = x;}
    public synchronized void setY(int y){this.y = y;}
}
