package nju.java;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Scorpion extends Monster {

    public Scorpion(int x, int y, Field field){
        super(x, y, field);
        this.attack = 4;
        this.speed = 2;

        URL loc = this.getClass().getClassLoader().getResource("Scorpion.jpg");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        //统一图片大小为100*100
        image =image.getScaledInstance(field.SPACE, field.SPACE, Image.SCALE_SMOOTH);
        this.setImage(image);

        String dead_pic_name = "dead" + "Scorpion.jpg";
        this.setDead_image(dead_pic_name);
    }

    @Override
    public String getName() {
        return "Scorpion";
    }
}
