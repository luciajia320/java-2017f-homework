package nju.java;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import nju.java.common.Constant;

import javax.swing.*;

public class TAdapter extends KeyAdapter {
    private Field field;
    public TAdapter(Field field1){
        field = field1;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_SPACE){
            if ( field.getState() == Constant.STATE.START) {
                field.StartPlay();
            }
        }
        else if(key == KeyEvent.VK_ESCAPE ){
            System.exit(0);
        }
        else if (key == KeyEvent.VK_R) {
            System.out.println();
            field.restartLevel();
        }
        else if (key == KeyEvent.VK_L) {
            if ( field.getState() == Constant.STATE.START) {
                Frame f = new Frame("FileDialog Test");
                FileDialog d1 = new FileDialog(field.getParentFrame(),
                        "打开文件对话框", FileDialog.LOAD );
                d1.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        d1.setVisible(false);
                    }
                });
                d1.setVisible(true);
                String filename = d1.getFile();
                String filedirectory = d1.getDirectory();
                System.out.println(filedirectory);
                System.out.println(filename);
                File file = new File(filedirectory + filename);
                Thread t1 = new Thread(new ReplayLoader(field, file));
                t1.start();
            }
        }
    }
}
