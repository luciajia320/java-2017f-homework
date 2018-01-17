package nju.java;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ImagePlayer {
    private Image[] images;
    private int cnt = 0;

    public ImagePlayer(String url, int bound){
        images = new Image[bound];

        for(int i = 0; i < bound; i++){
            System.out.println(url+"_"+i+".png");
            URL loc = this.getClass().getClassLoader().getResource(url+"_"+i+".png");
            if(loc == null) continue;
            ImageIcon iia = new ImageIcon(loc);
            images[i] = iia.getImage();
        }

    }

    public Image getNextImage(){
        cnt++;
        if(cnt == images.length) cnt = 0;
        return images[cnt];
    }
}
