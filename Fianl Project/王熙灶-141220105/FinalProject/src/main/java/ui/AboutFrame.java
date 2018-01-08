package ui;

import util.ImageReader;

import javax.swing.*;
import java.awt.*;

public class AboutFrame extends JFrame {
    private int width = 450;
    private int height = 300;
    private int modified_width = 450 + 6;
    private int modified_height = 300 + 29;

    public AboutFrame() {
        setTitle("About");
        setIconImage(ImageReader.getIcon("about.png").getImage());
        setLayout(null);
        setSize(modified_width, modified_height);

        JPanel panel = new AboutPanel();
        setContentPane(panel);

        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(false);
    }

    class AboutPanel extends JPanel {
        @Override
        public void paint(Graphics g) {
            g.drawImage(ImageReader.getImage("aboutbg4.png"), 0, 0, width, height, this);
        }
    }
}
