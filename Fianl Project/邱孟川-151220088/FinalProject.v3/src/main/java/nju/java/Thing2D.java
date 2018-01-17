package main.java.nju.java;

import java.awt.Image;

public class Thing2D {

    private final int SPACE = 40;

    private int x;
    private int y;
    private Image image;

    public Thing2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Image getImage() {
        return this.image;
    }

    public void setImage(Image img) {
        image = img;
    }

    public int imageX() {
        return this.x;
    }

    public int imageY() {
        return this.y;
    }

    public void setImageX(int x) {
        this.x = x;
    }

    public void setImageY(int y) {
        this.y = y;
    }


}