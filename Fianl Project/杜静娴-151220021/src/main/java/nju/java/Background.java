package nju.java;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Background extends Thing2D {
    Background(){
        super(0, 0);

        URL loc = this.getClass().getClassLoader().getResource("huluwa/bg.jpg");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }
}
