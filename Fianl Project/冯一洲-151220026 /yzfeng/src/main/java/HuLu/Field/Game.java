package HuLu.Field;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public final class Game extends JFrame {
    private final int WIDTH = 1000;
    private final int HEIGHT = 600;

    public Game() {
        InitUI();
    }

    public void InitUI() {
        Field field = new Field();
        add(field);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH,  HEIGHT);
        setLocationRelativeTo(null);
        setTitle("HuLuWa");
    }
}
