package nju.java;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.Random;
import javax.swing.ImageIcon;
/**
 * Created by Yuyang Wei on 2017/12/24.
 */
public class Xiezi extends Thing2D implements Runnable{
    private Field field;
    private char record[][]=new char[13][13];
    boolean isdead;
    public Xiezi(int x, int y,Field field,char record[][]) {
        super(x, y);
        this.field = field;
        this.record=record;
        isdead=false;
        URL loc = this.getClass().getClassLoader().getResource("boss.png");
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
            synchronized(Main.ground) {
                int old_y = (this.x() - 30) / 50;
                int old_x = (this.y() - 30) / 50;

                if(isdead==false&&(record[old_x-1][old_y]!='.'||record[old_x+1][old_y]!='.'||record[old_x+2][old_y]!='.'||record[old_x+3][old_y]!='.'))//encounter Huluwa
                {
                    isdead=true;
                    record[old_x][old_y]='D';//die

                    URL loc = this.getClass().getClassLoader().getResource("tombstone.jpg");
                    ImageIcon iia = new ImageIcon(loc);
                    Image image = iia.getImage();
                    this.setImage(image);

                    try {
                        File file = new java.io.File("HuLuWaRecord.txt");
                        PrintStream outfile = new PrintStream(new FileOutputStream(file, true));
                        for (int i = 0; i < 12; i++) {
                            outfile.print(record[i]);
                        }
                        outfile.println(record[12]);
                        outfile.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }

                if(isdead==false&&(x()==100+30)){
                    isdead=true;
                    record[old_x][old_y]='D';//die

                    URL loc = this.getClass().getClassLoader().getResource("tombstone.jpg");
                    ImageIcon iia = new ImageIcon(loc);
                    Image image = iia.getImage();
                    this.setImage(image);

                    savePic savepic=new savePic();
                    savepic.save();

                    try {
                        File file = new java.io.File("HuLuWaRecord.txt");
                        PrintStream outfile = new PrintStream(new FileOutputStream(file, true));
                        for (int i = 0; i < 12; i++) {
                            outfile.print(record[i]);
                        }
                        outfile.println(record[12]);
                        outfile.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }

                if(isdead==false&&x()>100+30) {
                    this.move(-50, 0);

                    int new_y = (this.x() - 30) / 50;
                    int new_x = (this.y() - 30) / 50;

                    record[old_x][old_y] = '.';
                    if(record[old_x][old_y]!='D'&&record[new_x][new_y]!='D') {
                        record[new_x][new_y] = 'X';
                    }

                    try {
                        File file = new java.io.File("HuLuWaRecord.txt");
                        PrintStream outfile = new PrintStream(new FileOutputStream(file, true));
                        for (int i = 0; i < 12; i++) {
                            outfile.print(record[i]);
                        }
                        outfile.println(record[12]);
                        outfile.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }

            }
            try {

                Thread.sleep(rand.nextInt(1000) + 1000);
                this.field.repaint();

            } catch (Exception e) {

            }
        }
    }
}
