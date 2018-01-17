package main.java.nju.java;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public class Tile extends Thing2D {

    public Tile(int x, int y) {
        super(x, y);

        URL loc = this.getClass().getClassLoader().getResource("resources/10.jpg");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        Image smallImage = image.getScaledInstance(SPACE,SPACE,Image.SCALE_FAST);
        this.setImage(smallImage);
    }
}