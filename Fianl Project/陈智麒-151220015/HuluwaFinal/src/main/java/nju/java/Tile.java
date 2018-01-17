package nju.java;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public class Tile extends Thing2D {

    private boolean isOccupted = false;
    private Creature holder = null;

    public void setOccupted(boolean isOccupted){
        this.isOccupted = isOccupted;
    }

    public boolean isOccupted(){
        return isOccupted;
    }

    public void setHolder(Creature creature){
        this.holder = creature;
    }

    public Creature getHolder(){
        return holder;
    }

    public Tile(int x, int y) {
        super(x, y);

        URL loc = this.getClass().getClassLoader().getResource("tile.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }
}