package nju.java;

import java.awt.Image;
import java.net.URL;
import java.util.Random;
import javax.swing.ImageIcon;

public class Huluwa extends Player{
    public static int num=0;
    private int no;
    public Huluwa(int x, int y, Field field) {
        super(x, y, field);
        no=++num;
        URL loc = this.getClass().getClassLoader().getResource(no+".jpg");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }
    public int  getNo(){return no;}
}
