package nju.java;

import org.junit.Test;

import javax.swing.*;
import java.io.File;

public class ReplayLoaderTest {
    @Test
    public void run() throws Exception {
        Field field = new Field();

        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.add(field);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(field.getBoardWidth() + 30,
                field.getBoardHeight() + 2 * 30);
        frame.setLocationRelativeTo(null);

        File file = new File("Best.txt");

        Thread t1 = new Thread( new ReplayLoader(field, file) );
        t1.start();
        t1.join();
    }

}