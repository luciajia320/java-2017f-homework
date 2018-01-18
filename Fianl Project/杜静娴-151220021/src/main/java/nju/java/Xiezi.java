package nju.java;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

public class Xiezi extends Player {

    public static void reInit() {
        count = 1;
    }

    private static int count = 1;
    {
        setTeam(false);
    }

    Xiezi(int x, int y, Field field) throws Exception {
        super(x-30,y,field);
        dir = false;
        if(count == 0)
            throw new Exception("Xiezi exists.");
        count--;
        URL loc = this.getClass().getClassLoader().getResource("huluwa/xiezi.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }

    public void reloadPath() {
        path = "huluwa/dxiezi.png";
        super.reloadPath();
    }

    @Override
    public String toString() {
        return new String("蝎子将领");
    }

    @Override
    public ArrayList<String> getLog() {
        ArrayList<String> list = new ArrayList<String>();
        for(State s : getRoute()) {
            list.add(this.printString()+" "+s.toString());
        }
        return list;
    }

    public String printString() {
        return super.toString();
    }
}

class Louluo extends Player {

    public static void reInit() {
        count = 0;
    }

    private static int count = 0;

    private int id = ++count;
    {
        setTeam(false);
    }

    Louluo(int x, int y, Field field) throws Exception {
        super(x,y+20,field);
        dir = false;
        URL loc = this.getClass().getClassLoader().getResource("huluwa/shu.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }

    public void reloadPath() {
        path = "huluwa/dshu.png";
        super.reloadPath();
    }

    @Override
    public String toString() {
        return new String("鼠喽啰"+id);
    }

    @Override
    public ArrayList<String> getLog() {
        ArrayList<String> list = new ArrayList<String>();
        for(State s : getRoute()) {
            list.add(this.printString()+" "+s.toString());
        }
        return list;
    }

    public String printString() {
        return super.toString()+id;
    }
}

class Shejing extends Player {

    public static void reInit() {
        count = 1;
    }

    private static int count = 1;

    {
        setTeam(false);
    }

    Shejing(int x, int y, Field field) throws Exception {
        super(x,y,field);
        dir = false;
        if(count == 0)
            throw new Exception("Shejing exists.");
        count--;
        URL loc = this.getClass().getClassLoader().getResource("huluwa/shejing.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }

    public void reloadPath() {
        path = "huluwa/dshejing.png";
        super.reloadPath();
    }

    @Override
    public String toString() {
        return new String("蛇精");
    }

    @Override
    public ArrayList<String> getLog() {
        ArrayList<String> list = new ArrayList<String>();
        for(State s : getRoute()) {
            list.add(this.printString()+" "+s.toString());
        }
        return list;
    }

    public String printString() {
        return super.toString();
    }
}