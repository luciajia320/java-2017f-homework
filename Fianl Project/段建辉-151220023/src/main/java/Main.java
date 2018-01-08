

import javax.swing.*;

public final class Main extends JFrame {

    private final int OFFSET = 30;

    public Main() {
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
        Main ground = new Main();
        ground.setVisible(true);
    }
}