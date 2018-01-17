package nju.java.common;

import javafx.scene.chart.PieChart;

import java.io.Serializable;

public class Data2D implements Serializable{
    protected int x;
    protected int y;

    public Data2D(int x,int y){
        this.x=x;
        this.y=y;
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
