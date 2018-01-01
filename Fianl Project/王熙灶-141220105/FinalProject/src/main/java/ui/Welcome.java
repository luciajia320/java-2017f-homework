package ui;

import util.ImageReader;

import javax.swing.*;
import java.awt.*;

public class Welcome extends JPanel {
    private Image background = ImageReader.getImage("background.png");
//    private Image background = ImageReader.getImage("grandpa-alive.png");
    private final int width = 1200;
    private final int height = 720;

    public Welcome() {
        setBounds(0, 0, width, height);
        setVisible(true);
        setSize(width, height);
        setLayout(null);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(background, 0, 0, width, height, null);
    }

    @Deprecated
    public void setButtons() {}
}
