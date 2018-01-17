package nju.java;

import org.junit.Test;

import javax.swing.*;
import java.awt.*;

public class HuluwaTest {

    @Test
    public void getImage() throws Exception {
        Field field = new Field();
        Huluwa h1 = new Huluwa(1,1, field, Huluwa.RANK.DaWa);
        Image img1 = h1.getImage();

        System.out.println(img1.getSource());

        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame();
        frame.setTitle("Simple Test for Huluwa Image get");
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel pic_label = new JLabel(new ImageIcon(img1));

        frame.add(pic_label);
        frame.pack();
        frame.setVisible(true);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}