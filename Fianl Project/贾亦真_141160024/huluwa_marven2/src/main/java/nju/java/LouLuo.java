package nju.java;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class LouLuo extends Monster {

    private static int count = 0;
    private int id;
    public LouLuo(int x, int y, Field field){
        super(x, y, field);
        this.attack = 3;
        this.speed = 3;
        id = count++;

        URL loc = this.getClass().getClassLoader().getResource("LouLuo.jpg");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        //统一图片大小为100*100
        image =image.getScaledInstance(field.SPACE, field.SPACE, Image.SCALE_SMOOTH);
        this.setImage(image);

        String dead_pic_name = "dead" + "LouLuo.jpg";
        this.setDead_image(dead_pic_name);
    }

    public int getId(){
        return id;
    }

    @Override
    public String getName() {
        return "LouLuo" + getId();
    }

}
