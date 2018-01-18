package main.java.nju.java;


import javax.swing.JFrame;


public final class Ground extends JFrame {

    private final int OFFSET = 30;


    public Ground(int n) {
        InitUI(n);
    }

    public void InitUI(int n) {
        Field field = new Field(n);
        add(field);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(field.getBoardWidth() + OFFSET,
                field.getBoardHeight() + 2 * OFFSET);
        setLocationRelativeTo(null);
        setTitle("Ground");
    }
}