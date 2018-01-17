package nju.java.creature;

import nju.java.common.GLOBAL;
import nju.java.common.cell;
import nju.java.creature.*;
import nju.java.field.BattleAttr;
import nju.java.field.Field;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

public class Huluwa extends Thing2D implements Solider,GoodPeople,Serializable{
    public Huluwa(cell postion, COLOR color, Field field, ArrayList<Animal> AnimalList){
        super(postion);
        this.color=color;
        this.field=field;
        this.c=postion;
        this.AnimalList=AnimalList;
        HuluwaSetImage();
        HuluwaSetDieImage();
        setPow(GLOBAL.HULUWA_POWER[color.ordinal()]);
        setID(color.ordinal());
        setRole(Role.GOOD);
    }

    public synchronized void moveTo(cell c) {
        this.c=c;
        position.setX(c.getX());
        position.setY(c.getY());
    }
    private ArrayList<Animal> AnimalList;

    //计算离其最近的敌人
    private int getAnimal(){
        int min=10000;
        int rst=-1;
        for(int i=0;i<AnimalList.size();i++){
            if(!AnimalList.get(i).isExit()) {
                int temp = Math.abs(this.getC().cellX - AnimalList.get(i).getC().cellX)
                        + Math.abs(this.getC().cellY - AnimalList.get(i).getC().cellY);
                if (temp < min) {
                    rst = i;
                    min = temp;
                }
            }

        }
        return rst;
    }
    private int getXDirection(int i){
        if(Math.abs(this.x()-AnimalList.get(i).x())==0)
            return 0;
        return -(this.x()-AnimalList.get(i).x())/Math.abs(this.x()-AnimalList.get(i).x());
    }
    private int getYDirection(int i){
        if(Math.abs(this.y()-AnimalList.get(i).y())==0)
            return 0;
        return -(this.y()-AnimalList.get(i).y())/Math.abs(this.y()-AnimalList.get(i).y());
    }

    public synchronized void run() {
        while (!exit) {
            try {
                int xdis=0,ydis=0;
                int tryTime=1000;
                    while (tryTime--!=0) {
                        int i=getAnimal();
                        if(i==-1){ return; }
                        xdis = getXDirection(i);
                        ydis = getYDirection(i);
                        if (field.getFieldGround(c.cellX + xdis, c.cellY + ydis) == BattleAttr.SPACE
                                &&c.cellX + xdis >= 0 && c.cellX + xdis < field.getXcount()
                                && c.cellY + ydis >= 0 && c.cellY + ydis < field.getYcount()) {
                            field.setFieldGround(c, new cell(c.cellX + xdis, c.cellY + ydis), BattleAttr.GOOD);
                            moveTo(new cell(c.cellX + xdis,c.cellY + ydis));
                            break;
                        }
                        xdis=0;
                    ydis=0;
                }
                Thread.sleep(GLOBAL.FREQUENT*(color.ordinal()+1));
                this.field.repaint();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public COLOR getColor() {
        return color;
    }
    public cell getC(){
        return c;
    }
    private transient Field field;
    private COLOR color;
    private cell c;
    private void HuluwaSetImage(){
        String fileName=color.toString()+".png";
        URL loc = this.getClass().getClassLoader().getResource(fileName);
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }
    private void HuluwaSetDieImage(){
        String fileName=color.toString()+"Die.png";
        URL loc = this.getClass().getClassLoader().getResource(fileName);
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setDieImage(image);
    }
}
