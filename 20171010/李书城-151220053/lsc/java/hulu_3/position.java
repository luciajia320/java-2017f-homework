package lsc.java.hulu_3;

//对二位空间中的位置单元及其相关操作进行定义
public class position{
    private int x;
    private int y;

    Creature holder;
    boolean occupied;

    public position()
    {
        occupied = false;
        x = -1;
        y  =-1;
    }
    public int fetch_x(){
        return x;
    }

    public int fetch_y(){
        return y;
    }

    public void set_x(int a){
        this.x = a;
    }

    public void set_y(int a){
        this.y = a;
    }
}
