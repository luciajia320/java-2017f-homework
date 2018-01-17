import javax.swing.*;
import java.awt.*;

public final class Ground extends JFrame {
    private final int OFFSET = 30;

    public Ground() {

        InitUI();

    }

    public void InitUI() {

        String strOut = "空格键开始\n" +
                "R键复位\n" +
                "L键读取\n" +
                "\n\n\n";

        Fight fight = new Fight();

        JTextArea text = new JTextArea();
        text.setText(strOut);
        add(text,BorderLayout.SOUTH);
        add(fight);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(fight.getBoardWidth() + OFFSET,

                fight.getBoardHeight() + 2 * OFFSET + 100);

        setLocationRelativeTo(null);

        setTitle("Huluwa's Story");

    }
}
