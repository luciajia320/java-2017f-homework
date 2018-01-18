package HuLu.Creature;

import HuLu.Field.Field;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.concurrent.CyclicBarrier;

enum Color{
    红, 橙, 黄, 绿, 蓝, 靛, 紫
}
enum Rank{
    老大, 老二, 老三, 老四, 老五, 老六, 老幺
}

public class HuLuWa extends GoodMan {
    Color color;
    Rank rank;

    public HuLuWa(int id , Field field, CyclicBarrier cyclic){
        super(id, field, cyclic);
        this.rank = Rank.values()[id];
        this.color = Color.values()[id];

        //load creature-living pic
        URL loc = this.getClass().getClassLoader().getResource( + id + ".png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();

        //load creature-die pic
        URL loc2 = this.getClass().getClassLoader().getResource(id + "dead.png");
        ImageIcon iia2 = new ImageIcon(loc2);
        Image image2 = iia2.getImage();

        Image[] images = new Image[2];
        images[0] = image;
        images[1] = image2;

        this.setImage(images);
    }
}
