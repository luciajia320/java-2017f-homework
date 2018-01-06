package ui;

import static util.Constant.*;
import static util.ImageReader.*;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    private final int WIDTH = 240;
    private final int HEIGHT = 720;

    public ControlPanel() {
        Font font = new Font("Microsoft YaHei UI", Font.PLAIN, 10);

        control_start = new JButton("Start", getIcon("start.png"));
        control_stop = new JButton("Stop", getIcon("stop.png"));
        control_reset = new JButton("Reset", getIcon("reset.png"));
        setLayout(null);

        JLabel label = new JLabel("Control");
        label.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 28));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setBounds(0, 0, 240, 60);
        add(label);

        control_start.setBounds(0, 60, 80, 40);
        control_start.setFont(font);
        control_start.addActionListener(e -> {
            control_start.setEnabled(false);
            control_stop.setEnabled(true);
            control_reset.setEnabled(true);
        });
        add(control_start);

        control_stop.setBounds(80, 60, 80, 40);
        control_stop.setFont(font);
        control_stop.setEnabled(false);
        control_stop.addActionListener(e -> {
            control_start.setEnabled(true);
            control_stop.setEnabled(false);
            control_reset.setEnabled(true);
        });
        add(control_stop);

        control_reset.setBounds(160, 60, 80, 40);
        control_reset.setFont(font);
        control_reset.setEnabled(false);
        control_reset.addActionListener(e -> {
            control_start.setEnabled(true);
            control_stop.setEnabled(false);
            control_reset.setEnabled(false);
        });
        add(control_reset);

        setBorder(BorderFactory.createEtchedBorder());
        setBackground(new Color(229, 237, 154));
        setVisible(true);
    }
}
