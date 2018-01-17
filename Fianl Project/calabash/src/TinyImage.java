import java.awt.*;

public class TinyImage {
    private int x;
    private int y;
    private Image image;

    public TinyImage(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {

        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
