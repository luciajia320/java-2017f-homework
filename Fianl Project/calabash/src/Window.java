import javax.swing.*;

public class Window extends JFrame {
    BattleField field = new BattleField();
    public Window(){
        add(field);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,200);
        setLocationRelativeTo(null);
        setTitle("葫芦娃");
    }
}
