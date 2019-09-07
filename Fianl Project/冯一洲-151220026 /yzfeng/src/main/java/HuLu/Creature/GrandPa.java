package HuLu.Creature;

import HuLu.Field.Field;
import HuLu.Field.GameState;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class GrandPa extends GoodMan {
    public GrandPa(int id , Field field, CyclicBarrier cyclic){
        super(id, field, cyclic);

        URL loc = this.getClass().getClassLoader().getResource( "grandpa.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();

        //load creature-die pic
        URL loc2 = this.getClass().getClassLoader().getResource("grandpadead.png");
        ImageIcon iia2 = new ImageIcon(loc2);
        Image image2 = iia2.getImage();

        Image[] images = new Image[2];
        images[0] = image;
        images[1] = image2;

        this.setImage(images);
    }
    public void run(){
        while (!Thread.interrupted() && field.getGameState() != GameState.END) {
            if(alive) {
                int rand = new Random().nextInt();
                if (rand % 4 == 0) {
                    if (validMove(0, 1))
                        move(0, 1);
                } else if (rand % 4 == 1) {
                    if (validMove(0, -1))
                        move(0, -1);
                }
            }
            try {
                cyclic.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }


}
