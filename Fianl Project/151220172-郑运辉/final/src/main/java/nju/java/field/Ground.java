package nju.java.field;

import nju.java.field.Field;

import javax.swing.*;

public final class Ground extends JFrame {

    private final int OFFSET = 30;
    private int width;
    private int height;
    private Field field;
    public Ground() {
        field = new Field();
        width=field.getBoardWidth();
        height=field.getBoardHeight();
        InitUI();
        setVisible(true);
    }

    public void InitUI() {
        setUndecorated(true);
        this.add(field);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(field.getBoardWidth(), field.getBoardHeight());
        setLocationRelativeTo(null);
        setTitle("Ground");
    }
}
