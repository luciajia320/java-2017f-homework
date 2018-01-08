package UI;
import Position.Position;

import java.awt.Image;

public class Thing2D {
    //坐标地址
    protected int x;
    protected int y;
    //像素地址
    private Position position;
    //图像
    private Image image;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Thing2D(int x, int y, Position position) {
        this.x=x;
        this.y=y;
        this.position=position;
    }

    public Image getImage() {
        return this.image;
    }

    public void setImage(Image img) {
        image = img;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}