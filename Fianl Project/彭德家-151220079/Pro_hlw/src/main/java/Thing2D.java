//package finalproject;

import java.awt.Image;

public class Thing2D {

    private final int SPACE = 20;

    private int x;
    private int y;
    private int type;
    private Image image;

    public Thing2D(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type=type;
    }
    public int getspace() {
    	return SPACE;
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
    
    public int type() {
        return this.type;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

}

