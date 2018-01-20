package nju.java;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
/**
 * Created by Yuyang Wei on 2017/12/22.
 */
public class Cloud extends Thing2D{
    public Cloud(int x, int y) {
        super(x, y);

        URL loc=this.getClass().getClassLoader().getResource("cloud.jpg");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }
}
