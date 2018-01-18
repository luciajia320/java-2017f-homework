import javax.swing.*;

public final class Ground extends JFrame {

    private final int OFFSET = 20;


    public Ground() {
        InitUI();
    }

    public void InitUI() {
        Field field = new Field();
        field.setLayout(null);
        add(field);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(field.getBoardWidth() + OFFSET,
                field.getBoardHeight() + 2 * OFFSET);
        setLocationRelativeTo(null);
        setTitle("葫芦兄弟");
    }
}