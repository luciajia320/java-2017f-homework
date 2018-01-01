package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class HuluMountainFrame extends JFrame {
    private Welcome welcome = new Welcome();
    private Ground ground = new Ground();
    private JButton enter = new JButton("进入游戏");
    private JButton replay = new JButton("回放记录");
    private MenuBar menuBar = new MenuBar();
    private final int width = 1200;
    private final int height = 720;

    public HuluMountainFrame() {
//        Toolkit kit = Toolkit.getDefaultToolkit();
//        Dimension dimension = kit.getScreenSize();
//        System.out.println(dimension.height);
//        System.out.println(dimension.width);
        setTitle("HuLu Mountain");
        setLayout(null);
        welcome.setBounds(0, 0, width, height);
        add(welcome);
        replay.setBounds(400, 100, 90, 40);
        welcome.add(replay);
        replay.addActionListener(e -> {
            System.out.println("Hello, world!");
        });

        enter.setBounds(600, 500, 90, 40);
        welcome.add(enter);
        enter.addActionListener(e -> {
            ground.setVisible(true);
            welcome.setVisible(false);
            setJMenuBar(menuBar);
        });

        ground.setBounds(0, 0, width, height);
        ground.setVisible(false);
        add(ground);

        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
//        addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentResized(ComponentEvent e) {
//                System.out.println(e.getComponent().getBounds().height);
//                System.out.println(e.getComponent().getBounds().width);
//                System.out.println(e.getComponent().getBounds().x);
//                System.out.println(e.getComponent().getBounds().y);
//            }
//        });
    }
}
