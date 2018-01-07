
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public class Tile extends Thing2D {

    public Tile(int x, int y) {
        super(x, y);

        URL url = getClass().getResource("resources/tile.png");
        ImageIcon iia = new ImageIcon(url);

        //ImageIcon iia = new ImageIcon("rresources/tile.png");
        Image image = iia.getImage();
        this.setImage(image);
    }
}