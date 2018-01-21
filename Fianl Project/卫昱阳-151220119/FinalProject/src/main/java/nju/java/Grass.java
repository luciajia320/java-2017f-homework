package nju.java;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
/**
 * Created by Yuyang Wei on 2017/12/21.
 */
public class Grass extends Thing2D{
    public Grass(int x, int y) {
        super(x, y);

        URL loc=this.getClass().getClassLoader().getResource("grass.jpg");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }

}
