package main.java.nju.creatures;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

import main.java.nju.game.Battle;

public abstract class Creature implements Runnable ,Serializable{

    public enum C_Type {
        None,grandpa,calabashdoll,soilder,scorpion};

    private int pos_x;  //位置横坐标 [0,MAX_X]
    private int pos_y;  //位置纵坐标 [0,MAX_Y]
    private boolean is_alive;   //是活着还是死了
    private C_Type Type;    //具体的Creature类型
    public Thread thread = null;
    protected ImageIcon image;
    protected Battle battle;

    public Creature(int x,int y,C_Type type,Battle battle){
        this.pos_x = x;
        this.pos_y = y;
        this.Type = C_Type.None;
        this.is_alive = true;
        this.battle = battle;
    }

    public void set_Thread(Thread thread){
        this.thread = thread;
    }

    public void set_Pos_X(int x){
        this.pos_x = x;
    }

    public void set_Pos_Y(int y){
        this.pos_y = y;
    }

    public void kill(){
        this.is_alive = false;
    }

    public void set_Image(ImageIcon image) {
        this.image = image;
    }

    public void set_Type(C_Type type){
        this.Type = type;
    }

    public int get_Pos_X(){
        return this.pos_x;
    }

    public int get_Pos_Y(){
        return this.pos_y;
    }

    public ImageIcon get_Image() {
        return image;
    }

    public C_Type get_Type(){
        return this.Type;
    }

    public boolean get_LiveStatus(){
        return this.is_alive;
    }

    public abstract void run();

    public boolean Attack(ArrayList<Creature> Evil){
        return false;
    }

}