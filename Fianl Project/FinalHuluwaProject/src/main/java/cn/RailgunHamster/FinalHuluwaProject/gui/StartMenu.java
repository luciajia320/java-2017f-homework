package cn.RailgunHamster.FinalHuluwaProject.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

class StartMenu extends JPanel {
    StartMenu() {
        this.setLayout(null);
        this.setSize(Materials.defaultSize);
        this.addBackground();
        this.addStartButton();
        this.addExitButton();
    }

    private Image background;

    private void addBackground() {
        this.background = new Materials().getImage("background", Materials.defaultSize);
    }

    private void addStartButton() {
        this.addButton("Start", new Rectangle(
                Materials.defaultSize.width * 3 / 5,
                Materials.defaultSize.height / 4,
                200, 100
                ), event -> App.game()
        );
    }

    private void addExitButton() {
        this.addButton("Exit", new Rectangle(
                Materials.defaultSize.width / 5,
                Materials.defaultSize.height * 3 / 4,
                200, 100
                ), event -> App.close()
        );
    }

    private void addButton(String name, Rectangle rectangle, ActionListener listener) {
        JButton button = new JButton(name);
        button.setBounds(rectangle);
        button.setFont(new Font("Arial", Font.PLAIN, 30));
        button.setForeground(new Color(184, 250, 255));
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.addActionListener(listener);
        button.setFocusable(false);
        this.add(button);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(background,0,0,null);
    }
}
