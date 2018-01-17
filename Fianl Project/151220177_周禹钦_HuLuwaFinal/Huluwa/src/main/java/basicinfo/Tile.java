package basicinfo;

/**
 * Created by qin on 18.1.3.
 */
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public class Tile extends Thing2D {

    public Tile(int x, int y) {
        super(x, y);
        //System.out.println(this.getClass());
        URL loc = this.getClass().getClassLoader().getResource("tile.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }
}
