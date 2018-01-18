package nju.java;

import java.awt.Image;
import java.net.URL;
import java.util.Random;
import javax.swing.ImageIcon;
/**
 * Created by Yuyang Wei on 2017/12/24.
 */
public class Grandfather extends Thing2D implements Runnable{
    private Field field;

    public Grandfather(int x, int y,Field field) {
        super(x, y);

        this.field=field;

        URL loc = this.getClass().getClassLoader().getResource("grandfather.jpg");
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
            //该方法的作用是生成一个随机的int值，该值介于[0,n)的区间，
            // 也就是0到n之间的随机int值，包含0而不包含n。
            try {

                Thread.sleep(rand.nextInt(1000) + 1000);
                this.field.repaint();

            } catch (Exception e) {

            }
        }
    }
}
