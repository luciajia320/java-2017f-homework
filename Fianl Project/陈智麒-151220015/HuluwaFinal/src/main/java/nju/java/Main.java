package nju.java;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public final class Main extends JFrame {

    private final int OFFSET = 30;


    public Main() {
        InitUI();
    }

    public void InitUI() {
        final Field field = new Field();
        add(field);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(field.getBoardWidth() + OFFSET,
                field.getBoardHeight() + 2 * OFFSET);
        setLocationRelativeTo(null);
        setTitle("葫芦娃大战妖精");
        Timer timer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                field.repaint();
            }
        });
        timer.start();
    }


    public static void main(String[] args) {
        Main ground = new Main();
        ground.setVisible(true);
    }
}