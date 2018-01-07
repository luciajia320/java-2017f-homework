

import javax.swing.*;
import java.awt.*;
import java.net.URL;


public final class Ground extends JFrame {

    private final int OFFSET = 30;

    public Ground() {
        InitUI();
    }

    public void InitUI() {

        Field field = new Field();
        add(field);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(field.getBoardWidth(), 700);
        setLocationRelativeTo(null);
        setTitle("HULUWA");
    }

    public static void main(String[] args) {
        Ground ground = new Ground();
        ground.setVisible(true);
    }
}