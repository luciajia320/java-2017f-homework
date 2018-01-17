import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public class grass {

    private final int SPACE = 20;

    private int x;
    private int y;
    private Image image;

    public grass(int x, int y) {
        this.x = x;
        this.y = y;
        URL loc = this.getClass().getClassLoader().getResource("grass.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }

    public Image getImage() {
        return this.image;
    }

    public void setImage(Image img) {
        image = img;
    }

    public int x() {
        return this.x;
    }

    public int y() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


} 