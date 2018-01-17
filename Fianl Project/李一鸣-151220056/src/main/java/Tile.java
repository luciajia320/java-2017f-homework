import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Tile extends  Creature {
    public Tile(int x, int y) {
        setPosition(new Position(x,y));
        URL loc = this.getClass().getClassLoader().getResource("7.jpg");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }
}
