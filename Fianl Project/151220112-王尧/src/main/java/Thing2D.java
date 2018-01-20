import java.awt.Image;
import java.io.FileWriter;
import java.io.IOException;

public class Thing2D {

    private final int SPACE = 20;

    private int x;
    private int y;
    private Image image;
    private Image deadImage;

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

    public Image getDeadImage() {
        return this.deadImage;
    }

    public void setDeadImage(Image img) {
        deadImage = img;
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

    /*
    public void record(FileWriter file) {
        try {
            file.write(x + " " + y + " ");
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
    */
} 