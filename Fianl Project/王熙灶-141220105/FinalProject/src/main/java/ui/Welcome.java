package ui;

import util.ImageReader;
import util.Constant;

import javax.swing.*;
import java.awt.*;

import static util.Constant.welcome_enter;
import static util.Constant.welcome_help;
import static util.Constant.welcome_replay;
import static util.ImageReader.getIcon;

public class Welcome extends JPanel {
    private Image background = ImageReader.getImage("background3.png");
//    private Image background = ImageReader.getImage("grandpa-alive.png");
    private final int width = Constant.WIDTH;
    private final int height = Constant.HEIGHT;

    public Welcome() {
        setLayout(null);

        welcome_enter = new JButton(getIcon("b_enter.png"));
        welcome_enter.setBounds(550, 300, 120, 42);
        welcome_enter.setBorderPainted(false);
        welcome_enter.setFocusPainted(false);
        add(welcome_enter);

        welcome_replay = new JButton(getIcon("b_replay.png"));
        welcome_replay.setBounds(550, 400, 120, 42);
        welcome_replay.setBorderPainted(false);
        welcome_replay.setFocusPainted(false);
        add(welcome_replay);

        welcome_help = new JButton(getIcon("b_help.png"));
        welcome_help.setBounds(550, 500, 120, 42);
        welcome_help.setBorderPainted(false);
        welcome_help.setFocusPainted(false);
        add(welcome_help);

        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(background, 0, 0, width, height, this);
        welcome_enter.repaint();
        welcome_replay.repaint();
        welcome_help.repaint();
    }

    @Deprecated
    public void setButtons() {}
}
