package mainroles;
import battlefield.Field;
import battlefield.Position;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Random;
public class Huluwa implements Creature {
    String name;
    public  int fileid = 1;
    private Position p;
    private Position beforep;
    private boolean inBattle = false;
    private boolean inlive = true;
    public static Image ig;
    public static Image deathIg;
    private boolean go = false;
    private static boolean justice = true;
    private direction dir = direction.right;
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
    public Image getImage()
    {
        return ig;
    }
    @Override
    public void setGo()
    {
        go = true;
    }
    public Huluwa(String name,Field f,Position p)
    {
        this.name = name;
        this.fields = f;
        this.p = p;
        this.inlive = true;
        p.setHolder(this);
/*        URL loc = this.getClass().getClassLoader().getResource("huluwa.png");
        ImageIcon iia = new ImageIcon(loc);
        ig = iia.getImage();
        ig = ig.getScaledInstance(60,60,Image.SCALE_DEFAULT);*/
    }
    @Override
    public boolean getLive()
    {
        return inlive;
    }

    @Override
    public void beKilled()
    {
        this.inlive = false;
    }
    @Override
    public void setBattle(boolean battle)
    {
        this.inBattle = battle;
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
    public Image getDeathImage()
    {
        return deathIg;
    }
    @Override
    public void setFileId(int id)
    {
        this.fileid = id;
    }
    @Override
    public void run()
    {
        while(inlive)
        {
            if(inBattle == false&&go) {
                beforep = p;
                Creature c;
                if(dir == direction.right) {
                    if (fields.setPosition(p.getX() + 1, p.getY(), this)) {
                        beforep.clearHolder();
                    }
                    else if((c = fields.getCreature(p.getX()+1,p.getY()))!=null)
                    {
                        if(c.getJustice()==false&&c.getLive()==true)
                        {
                            c.setBattle(true);
                            this.setBattle(true);
                            Random r = new Random();
                            int i = r.nextInt(100);
                            if(i<80)
                            {
                                c.beKilled();
                                c.setFileId(c.getFileId()+5);
                                this.setBattle(false);
                            }
                            else
                            {
                                this.beKilled();
                                this.setFileId(this.getFileId()+5);
                                c.setBattle(false);
                            }
                        }
                        else dir = direction.up;
                    }
                    else {
                        dir = direction.up;
                    }
                }
                else if(dir==direction.up) {
                    if (fields.setPosition(p.getX(), p.getY() - 1, this))
                        beforep.clearHolder();
                    else if((c = fields.getCreature(p.getX(),p.getY()-1))!=null)
                    {
                        if(c.getJustice()==false&&c.getLive()==true)
                        {
                            c.setBattle(true);
                            this.setBattle(true);
                            Random r = new Random();
                            int i = r.nextInt(100);
                            if(i<80)
                            {
                                c.beKilled();
                                //c.getPosition().clearHolder();
                                c.setFileId(c.getFileId()+5);
                                this.setBattle(false);
                            }
                            else
                            {
                                this.beKilled();
                                //beforep.clearHolder();
                                this.setFileId(this.getFileId()+5);
                                c.setBattle(false);
                            }
                        }
                        else dir = direction.left;
                    }
                    else
                        dir = direction.left;
                }
                else if(dir==direction.left) {
                    if (fields.setPosition(p.getX() - 1, p.getY(), this))
                        beforep.clearHolder();
                    else if((c = fields.getCreature(p.getX()-1,p.getY()))!=null)
                    {
                        if(c.getJustice()==false&&c.getLive()==true)
                        {
                            c.setBattle(true);
                            this.setBattle(true);
                            Random r = new Random();
                            int i = r.nextInt(100);
                            if(i<80)
                            {
                                c.beKilled();
                                //c.getPosition().clearHolder();
                                c.setFileId(c.getFileId()+5);
                                this.setBattle(false);

                            }
                            else
                            {
                                this.beKilled();
                                //beforep.clearHolder();
                                this.setFileId(this.getFileId()+5);
                                c.setBattle(false);
                            }
                        }
                        else dir = direction.down;
                    }
                    else
                        dir = direction.down;
                }
                else if(dir==direction.down) {
                    if (fields.setPosition(p.getX(), p.getY() + 1, this))
                        beforep.clearHolder();
                    else if((c = fields.getCreature(p.getX(),p.getY()+1))!=null)
                    {
                        if(c.getJustice()==false&&c.getLive()==true)
                        {
                            c.setBattle(true);
                            this.setBattle(true);
                            Random r = new Random();
                            int i = r.nextInt(100);
                            if(i<80)
                            {
                                c.beKilled();
                                //c.getPosition().clearHolder();
                                c.setFileId(c.getFileId()+5);
                                this.setBattle(false);
                            }
                            else
                            {
                                this.beKilled();
                                //beforep.clearHolder();
                                this.setFileId(this.getFileId()+5);
                                c.setBattle(false);
                            }
                        }
                        else dir = direction.right;
                    }
                    else dir = direction.right;
                }
                go = false;
            }
            Thread.yield();
        }
        System.out.println("exit: " + name);
    }
}
