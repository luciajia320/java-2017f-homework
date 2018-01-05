package ui;

import util.Constant;
import util.GroundState;
import util.ImageReader;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class HuluMountainFrame extends JFrame {
    private Welcome welcome = new Welcome();
    private Ground ground = new Ground();
    private JButton enter = new JButton("进入游戏");
    private JButton replay = new JButton("回放记录");
    private JButton help = new JButton("游戏帮助");
    private JPanel buttonpanel = new JPanel();
    private ButtonGroup group = new ButtonGroup();
    private MenuBar menuBar = new MenuBar();
    private final int width = Constant.WIDTH;
    private final int height = Constant.HEIGHT;

    public HuluMountainFrame() {
        setTitle("HuLu Mountain");
        setIconImage(ImageReader.getIcon("hulu.png").getImage());
        setLayout(null);
//        welcome.setBounds(0, 0, width, height);
        setSize(width, height);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        welcome.setBounds(0, 0, width, height);
        getContentPane().add(welcome);
        setVisible(true);
        System.out.println(welcome.getWidth());
        System.out.println(welcome.getHeight());

        group.add(enter);
        group.add(replay);
        group.add(help);

        enter.setBounds(550, 300, 90, 40);
//        welcome.add(enter);
//        enter.repaint();
        enter.addActionListener(e -> {
//            ground.setVisible(true);
//            welcome.setVisible(false);
            setJMenuBar(menuBar);
            remove(welcome);
            remove(buttonpanel);
            ground.setBounds(0, 0, 960, 720);
            add(ground);
            setVisible(true);
            System.out.println(ground.getWidth());
            System.out.println(ground.getHeight());
        });

        replay.setBounds(550, 400, 90, 40);
//        welcome.add(replay);
//        replay.repaint();
        replay.addActionListener(e -> System.out.println("Hello, world!"));

        help.setBounds(550, 500, 90, 40);
//        welcome.add(help);
//        help.repaint();
        help.addActionListener(e -> System.out.println("Hello, world!"));

        buttonpanel.setLayout(null);
//        buttonpanel.setBounds(500,200,190,390);
        buttonpanel.setBounds(0,0,1200,720);
        buttonpanel.add(enter);
        enter.setVisible(true);
        buttonpanel.add(replay);
        replay.setVisible(true);
        buttonpanel.add(help);
        help.setVisible(true);
        buttonpanel.setVisible(true);
        add(buttonpanel);
//        welcome.repaint();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if(ground.getState() == GroundState.READY) {
                        ground.run();
                    }
                }
            }
        });

        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        validate();
    }
}
