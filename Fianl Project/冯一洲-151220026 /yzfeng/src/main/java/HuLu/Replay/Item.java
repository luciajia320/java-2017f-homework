package HuLu.Replay;

import HuLu.Creature.Thing2D;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Item extends Thing2D {
    private int id;
    private String alive;

    public Item(int id, int x, int y, String alive){
        this.id = id;
        this.x = x;
        this.y = y;
        this.alive = alive;
        String name;
        if(id < 7)
            name = new Integer(id).toString();
        else if(id == 7)
            name = "grandpa";
        else if(id >= 8 && id < 12)
            name = "scorpion";
        else if(id >= 12 && id <=14)
            name = "louluo";
        else name = "snake";
        URL loc = this.getClass().getClassLoader().getResource( name + ".png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();

        //load creature-die pic
        URL loc2 = this.getClass().getClassLoader().getResource(name + "dead.png");
        ImageIcon iia2 = new ImageIcon(loc2);
        Image image2 = iia2.getImage();

        Image[] images = new Image[2];
        images[0] = image;
        images[1] = image2;

        this.setImage(images);
    }
    public Image getImage() {
        if(alive.equals("true")){
            return super.image[0];
        }
        else{
            return super.image[1];
        }
    }

    public int x() {
        return this.x;
    }

    public  int y() {
        return this.y;
    }

    public String isAlive() {
        return alive;
    }



}