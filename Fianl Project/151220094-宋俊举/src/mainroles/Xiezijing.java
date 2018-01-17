package mainroles;

import battlefield.Field;
import battlefield.Position;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Random;

public class Xiezijing implements Creature {
    String name;
    public  int fileid = 2;
    private Position p;
    private Position beforep;
    private boolean inBattle = false;
    private boolean inlive = true;
    public static Image ig;
    public static Image deathIg;
    private boolean go = false;
    private static boolean justice = false;
    private direction dir = direction.left;
    Field fields;
    @Override
    public boolean getJustice()
    {
        return justice;
    }
    @Override
    public int getFileId()
    {
        return fileid;
    }
    @Override
    public void setFileId(int id)
    {
        this.fileid = id;
    }
    @Override
    public Image getDeathImage()
    {
        return deathIg;
    }
    @Override
    public Image getImage()
    {
        return ig;
    }
    @Override
    public void setGo()
    {
        go = true;
    }
    public Xiezijing(String name,Field f,Position p)
    {
        this.name = name;
        this.fields = f;
        this.p = p;
        this.inlive = true;
        p.setHolder(this);
/*        URL loc = this.getClass().getClassLoader().getResource("xiezijing.png");
        ImageIcon iia = new ImageIcon(loc);
        ig = iia.getImage();
        ig = ig.getScaledInstance(60,60,Image.SCALE_DEFAULT);*/
    }
    @Override
    public void beKilled()
    {
        this.inlive = false;
    }

    @Override
    public Position getPosition()
    {
        return p;
    }
    @Override
    public void setPosition(Position p)
    {
        this.p = p;
        p.setHolder(this);
    }
    @Override
    public boolean getLive()
    {
        return inlive;
    }
    @Override
    public void setBattle(boolean battle)
    {
        this.inBattle = battle;
    }


    @Override
    public void run()
    {
        while(inlive)
        {
            if(inBattle == false&&go) {
                beforep = p;
                if(dir == direction.left) {
                    if (fields.setPosition(p.getX() - 1, p.getY(), this)) {
                        beforep.clearHolder();
                    } else {
                        dir = direction.up;
                    }
                }
                else if(dir==direction.up) {
                    if (fields.setPosition(p.getX(), p.getY() - 1, this))
                        beforep.clearHolder();
                    else
                        dir = direction.right;
                }
                else if(dir==direction.right) {
                    if (fields.setPosition(p.getX() + 1, p.getY(), this))
                        beforep.clearHolder();
                    else
                        dir = direction.down;
                }
                else if(dir==direction.down) {
                    if (fields.setPosition(p.getX(), p.getY() + 1, this))
                        beforep.clearHolder();
                    else dir = direction.left;
                }
                go = false;
            }
            Thread.yield();
        }
    }
}
