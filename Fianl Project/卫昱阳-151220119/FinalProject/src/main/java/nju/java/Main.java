package nju.java;

import javax.swing.*;
import java.applet.AudioClip;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Yuyang Wei on 2017/12/21.
 */
public class Main extends JFrame {
    private final int OFFSET=30;

    public Main() throws FileNotFoundException {
        InitUI();
    }

    public static AudioClip loadSound() {

        URL url = null;
        try {
            url = new URL("file:" + "huluwa.wav");
        }
        catch (MalformedURLException e) {;}
        return JApplet.newAudioClip(url);
    }
    public static void play(){
        AudioClip huluwa=loadSound();
        huluwa.play();
    }

    public void InitUI() throws FileNotFoundException {
        Field field=new Field();
        add(field);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(field.getBoardWidth()+OFFSET,
                field.getBoardHeight()+2*OFFSET
        );
        setLocationRelativeTo(null);
        setTitle("葫芦娃大战妖精");
    }//InitUI

    static Main ground;

    public static void main(String[] args) throws FileNotFoundException {
        Main.play();
        ground = new Main();
        ground.setVisible(true);
    }
}
