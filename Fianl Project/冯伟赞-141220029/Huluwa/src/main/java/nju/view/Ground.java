package nju.view;

import nju.constant.AppConfig;
import nju.util.Log;

import javax.swing.*;

public class Ground extends JFrame {

    private static final String TAG = "Ground";


    private static final int OFFSET = 40;

    public Ground() {
        init();
    }


    public void init() {
        Field field = new Field();
        add(field);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(OFFSET + field.getBoardWidth(),
                OFFSET + field.getBoardHeight());

        setLocationRelativeTo(null);
        setTitle(AppConfig.TITLE);
        Log.d(TAG, field.getPlat().toString());

    }
}
