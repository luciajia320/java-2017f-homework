package HuLu.Creature;

import java.awt.*;

public class Thing2D {
    protected int x, y;

    private final int SPACE = 20;

    protected Image[] image = new Image[2];

    public Image getImage(int index) {
        return this.image[index];
    }

    public void setImage(Image[] img) {
        image = img;
    }
}
