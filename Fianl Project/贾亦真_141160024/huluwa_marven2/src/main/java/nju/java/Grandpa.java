package nju.java;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Grandpa extends Human{

    public Grandpa(int x, int y, Field field){
        super(x, y, field);
        this.attack = 1;
        this.speed = 2;

        URL loc = this.getClass().getClassLoader().getResource("Grandpa.jpg");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        //统一图片大小为100*100
        image =image.getScaledInstance(field.SPACE, field.SPACE, Image.SCALE_SMOOTH);
        this.setImage(image);

        String dead_pic_name = "dead" + "Grandpa.jpg";
        this.setDead_image(dead_pic_name);
    }

    @Override
    public String getName() {
        return "Grandpa";
    }
}
