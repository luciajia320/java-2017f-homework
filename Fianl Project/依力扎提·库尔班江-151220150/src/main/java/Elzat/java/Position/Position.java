package Position;

import creature.Creature;

//此类中的XY为实际像素的XY
public class Position {
    int x,y;
    Creature someone;


    public     Position(int x,int y){
        this.x=x;
        this.y=y;
        someone=null;
    }
    public Position (int x,int y,Creature s)
    {
        this.x=x;
        this.y=y;
        someone=s;
    }

    public Position(Creature s)
    {
        someone=s;
    }

    public Position(){;}

    public Creature getSomeone(){return someone;}
    public int getX(){return x;}
    public int getY(){return y;}

    public void setSomeone(Creature s){someone=s;}
    public void  setX(int x){this.x=x;}
    public void  setY(int y){this.y=y;}
}