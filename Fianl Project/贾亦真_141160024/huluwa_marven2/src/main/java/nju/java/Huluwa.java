package nju.java;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Huluwa extends Human {
    private RANK rank;
    private COLOR cl;

    public enum RANK{
        DaWa,ErWa,SanWa,SiWa,WuWa,LiuWa,QiWa
    }

    public enum COLOR{
        Red,Orange,Yellow,Green,Teal,Blue,Purple
    }

    Huluwa(int x, int y, Field field, RANK rk){
        super(x, y, field);
        this.attack = 5;
        this.speed = 5;

        rank = rk;
        cl = COLOR.values()[rk.ordinal()];

        //Set Alive Image
        String pic_name = rk.toString() + ".jpg";
        URL loc = this.getClass().getClassLoader().getResource(pic_name);
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        //统一图片大小为100*100
        image =image.getScaledInstance(field.SPACE, field.SPACE, Image.SCALE_SMOOTH);
        this.setImage(image);

        //Set Dead Image
        String dead_pic_name = "dead" + pic_name;
        this.setDead_image(dead_pic_name);
    }

    public RANK getRank(){
        return rank;
    }

    @Override
    public String getName() {
        return this.rank.toString();
    }
}
