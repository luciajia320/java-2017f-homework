package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Test extends JFrame{

    MyPanel mp=null;
    public Test(){
        mp=new MyPanel();
        this.add(mp);
        this.setSize(550, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args){
        new Test();
    }
}

class MyPanel extends JPanel {

    Image image=null;

    public void paint(Graphics g){
        try {
            image= ImageIO.read(new File("src/main/resources/grass.png"));
            g.drawImage(image, 0, 0, 550, 400, null);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}