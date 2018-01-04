import javax.swing.JFrame;

public final class GameWindow extends JFrame{
    private final int OFFSET = 30;


    public GameWindow(int width, int height) {
        setSize(width, height);
        InitUI();
    }

    public void InitUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);
        setTitle("Ground");
    }
}
