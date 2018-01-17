import java.awt.Image;
import java.net.URL;
import java.util.Random;
import javax.swing.ImageIcon;

public class Player extends Thing2D implements Runnable {
    private Fight fight;

    public Player(int x, int y, Fight fight) {
        super(x, y);

        this.fight = fight;

        URL loc = this.getClass().getClassLoader().getResource("player.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }

    public void move(int x, int y) {
        int nx = this.x() + x;
        int ny = this.y() + y;
        this.setX(nx);
        this.setY(ny);
    }

    public void run() {
        while (!Thread.interrupted()) {
            Random rand = new Random();

            this.move(rand.nextInt(10), rand.nextInt(10));
            try {

                Thread.sleep(rand.nextInt(1000) + 1000);
                this.fight.repaint();

            } catch (Exception e) {

            }
        }
    }
}