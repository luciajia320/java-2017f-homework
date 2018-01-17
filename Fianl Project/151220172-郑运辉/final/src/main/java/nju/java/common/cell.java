package nju.java.common;

import java.io.Serializable;

public class cell extends Data2D implements Serializable{
    public cell(int x,int y){
        super(x*Xlength+Xoffset,y*Ylength+Yoffset);
        cellX=x;
        cellY=y;
        devX=x*Xlength;
        devY=y*Ylength;
    }

    public boolean isNeig(cell c){
        if (Math.abs(c.cellX - cellX) <= GLOBAL.TIMES && Math.abs(c.cellY - cellY) <= GLOBAL.TIMES) {
            return true;
        } else {
            return false;
        }
    }

    public int cellX;
    public int cellY;
    public int devX;
    public int devY;
    //偏移量
    public final static int Xoffset=153;//153-1165,22个格子
    public final static int Yoffset=508-100;//478-767,7个格子
    //一个方块
    public final static int Xlength=92/ GLOBAL.TIMES;
    public final static int Ylength=37/ GLOBAL.TIMES;
}
