package nju.java;

import java.awt.*;

import static java.lang.Thread.sleep;

public class Grandpa extends GoodMan {
    public Grandpa(int x, int y, Field field) {
        super(x, y, field, "grandpa");
    }

    //private ImagePlayer imagewatchings = new ImagePlayer("player", 1);

    private ImagePlayer imagewatchings = new ImagePlayer("grandpa_watching", 1);
    private int cnt = 0;
    private boolean isGoRight = true;

    public String toString(){
        return "Grandpa\t0";
    }

    @Override
    public Image getImage() throws Exception {
        if(this.isDead()) return null;
        return imagewatchings.getNextImage();
    }

    @Override
    public void run(){
        while(!Thread.interrupted()) {
            if (isGoRight == true) {
                this.setX(this.x() + 1);
                cnt++;
                if (cnt >= 100) {
                    isGoRight = false;
                }
            } else {
                this.setX(this.x() - 1);
                cnt--;
                if (cnt <= 0) {
                    isGoRight = true;
                }
            }
            try {
                sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
