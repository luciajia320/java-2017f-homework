package FinalProject;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public class Item extends Thing2D {

	public Item(int x, int y, int t) {
        super(x, y);
        URL loc = this.getClass().getClassLoader().getResource("item"+t+".png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }
}
