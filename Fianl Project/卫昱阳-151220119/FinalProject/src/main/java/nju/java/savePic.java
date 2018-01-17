package nju.java;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Yuyang Wei on 2018/1/8.
 */
public class savePic {
    private Main ground;
    public savePic(){
        this.ground=Main.ground;
    }
    public void save(){
        //得到窗口内容面板
        Container content=ground.getContentPane();
        //创建缓冲图片对象
        BufferedImage img=new BufferedImage(
                ground.getWidth(),ground.getHeight(),BufferedImage.TYPE_INT_RGB);
        //得到图形对象
        Graphics2D g2d = img.createGraphics();
        //将窗口内容面板输出到图形对象中
        content.printAll(g2d);
        //保存为图片
        //File f=new File("saveScreen.jpg");
        File f=new File(Field.Pic_count+".jpg");
        Field.Pic_count++;
        try {
            ImageIO.write(img, "jpg", f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //释放图形对象
        g2d.dispose();
    }
}
