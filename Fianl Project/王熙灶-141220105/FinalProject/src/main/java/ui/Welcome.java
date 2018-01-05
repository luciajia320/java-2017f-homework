package ui;

import util.ImageReader;
import util.Constant;

import javax.swing.*;
import java.awt.*;

public class Welcome extends JPanel {
    private Image background = ImageReader.getImage("background.png");
//    private Image background = ImageReader.getImage("grandpa-alive.png");
    private final int width = Constant.WIDTH;
    private final int height = Constant.HEIGHT;

    public Welcome() {
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(background, 0, 0, width, height, this);
    }

    @Deprecated
    public void setButtons() {}
}
