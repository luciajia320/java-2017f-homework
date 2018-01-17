

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public class Tile extends Thing2D {

    public Tile(int x, int y,Role r) {
        super(x, y,r);

        URL loc = this.getClass().getClassLoader().getResource("./img/ground.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }
}