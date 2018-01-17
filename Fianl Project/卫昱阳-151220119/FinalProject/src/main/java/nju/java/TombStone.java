package nju.java;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Created by Yuyang Wei on 2018/1/5.
 */
public class TombStone extends Thing2D{
    public TombStone(int x, int y) {
        super(x, y);

        URL loc=this.getClass().getClassLoader().getResource("tombstone.jpg");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }
}
