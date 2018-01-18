/**
 * Created by qin on 18.1.4.
 */
import  play.Field;

import javax.swing.JFrame;
public class Main extends JFrame{
    private final int OFFSET = 30;


    public Main() {
        InitUI();
    }

    public void InitUI() {
        Field field = new Field();
        add(field);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(field.getBoardWidth() + OFFSET,
                field.getBoardHeight() + 2 * OFFSET);
        setLocationRelativeTo(null);
        setTitle("葫芦娃大战妖精");
    }


    public static void main(String[] args) {
         Main ground = new Main();
        ground.setVisible(true);
    }
}
