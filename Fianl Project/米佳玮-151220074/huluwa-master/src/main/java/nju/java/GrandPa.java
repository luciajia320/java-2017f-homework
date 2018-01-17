package nju.java;

import java.awt.Image;
import java.net.URL;
import java.util.Random;
import javax.swing.ImageIcon;

public class GrandPa extends Player{
    public GrandPa(int x, int y, Field field) {
        super(x, y, field);
        URL loc = this.getClass().getClassLoader().getResource("grandpa.jpg");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }
}
