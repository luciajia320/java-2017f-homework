package nju.java;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Yuyang Wei on 2018/1/8.
 */
public class PlayBack implements Runnable{
    private ArrayList<File>saved;
    private int back_count=0;
    private Field field;

    PlayBack(Field field){
        this.field=field;
        //File fs=new File(savePic.url);
        saved=new ArrayList<File>();
        for(int i=0;i<Field.Pic_count;i++){
            String path=String.valueOf(i)+".jpg";
            File temp=new File(path);
            saved.add(temp);
        }
    }

    public void init_back_count(){back_count=0;}
    public void play(Graphics g){
        if(back_count<saved.size()){
            BufferedImage image=null;
            try {
                image= ImageIO.read(saved.get(back_count));
            }catch (IOException e){System.out.println("Load Error!");}

            g.setColor(Color.WHITE);
            g.fillRect(0,0,field.getWidth(),field.getHeight());
            g.drawImage(image,0,0,field);
            back_count++;
        }
    }

    public void run() {
        while (!Thread.interrupted()&&back_count<saved.size()){
            field.repaint();
            try {
                Thread.sleep(100);
            }catch (Exception e){}
        }
        Thread.interrupted();
        field.initWorld();
    }
}
