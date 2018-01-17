package nju.java.creature;


import nju.java.common.GLOBAL;
import nju.java.common.cell;
import nju.java.field.*;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

public class Animal extends Thing2D implements Solider,BadAnimal,Serializable {


    public Animal(cell postion, BAD bad, Field field, ArrayList HuluwaList){
        super(postion);
        this.bad=bad;
        this.field=field;
        this.c=postion;
        this.HuluwaList=HuluwaList;
        AnimalSetImage();
        AnimalSetDieImage();
        setPow(GLOBAL.ANIMAL_POWER[bad.ordinal()]);
        setID(0);
        setRole(Role.BAD);
    }

    public synchronized void moveTo(cell c) {
        this.c=c;
        position.setX(c.getX());
        position.setY(c.getY());
    }

    private ArrayList<Huluwa> HuluwaList;
    private int getHuluwa(){
        int min=10000;
        int rst=-1;
        for(int i=0;i<HuluwaList.size();i++){
            if(!HuluwaList.get(i).isExit()){
                int temp=Math.abs(this.getC().cellX-HuluwaList.get(i).getC().cellX)
                        +Math.abs(this.getC().cellY-HuluwaList.get(i).getC().cellY);
                if(temp < min){
                    rst=i;
                    min=temp;
                }
            }
        }
        return rst;
    }
    private int getXDirection(int i){
        if(Math.abs(this.x()-HuluwaList.get(i).x())==0)
            return 0;
        return -(this.x()-HuluwaList.get(i).x())/Math.abs(this.x()-HuluwaList.get(i).x());
    }
    private int getYDirection(int i){
        if(Math.abs(this.y()-HuluwaList.get(i).y())==0)
            return 0;
        return -(this.y()-HuluwaList.get(i).y())/Math.abs(this.y()-HuluwaList.get(i).y());
    }
    public synchronized void run() {
        while (!exit) {
            try {
                int xdis=0,ydis=0;
                int tryTime=1000;
                    while (tryTime--!=0) {
                        int i=getHuluwa();
                        if(i==-1){ return; }
                        xdis = getXDirection(i);
                        ydis = getYDirection(i);
                        if (field.getFieldGround(c.cellX + xdis, c.cellY + ydis) == BattleAttr.SPACE
                                &&c.cellX + xdis >= 0 && c.cellX + xdis < field.getXcount()
                                && c.cellY + ydis >= 0 && c.cellY + ydis < field.getYcount()) {
                        field.setFieldGround(c, new cell(c.cellX + xdis, c.cellY + ydis), BattleAttr.BAD);
                        moveTo(new cell(c.cellX + xdis,c.cellY + ydis));
                        break;
                    }
                    xdis=0;
                    ydis=0;
                }
                Thread.sleep(GLOBAL.FREQUENT*(bad.ordinal()+1));
                this.field.repaint();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public cell getC(){
        return c;
    }
    private void AnimalSetImage() {
        String fileName=bad.toString()+".png";
        URL loc = this.getClass().getClassLoader().getResource(fileName);
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }
    private void AnimalSetDieImage(){
        String fileName="die.png";
        URL loc = this.getClass().getClassLoader().getResource(fileName);
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setDieImage(image);
    }
    private BAD bad;
    private transient Field field;
    private cell c;
}
