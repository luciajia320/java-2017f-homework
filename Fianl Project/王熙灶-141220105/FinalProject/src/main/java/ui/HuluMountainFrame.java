package ui;

import util.ArchiveIO;
import util.Constant;
import util.GroundState;
import util.ImageReader;

import javax.swing.*;
import java.awt.event.*;

import static util.Constant.*;

public class HuluMountainFrame extends JFrame {
    private final int width = Constant.WIDTH;
    private final int height = Constant.HEIGHT;

    public HuluMountainFrame() {
        menuBar = new MenuBar();
        setTitle("HuLu Mountain");
        setIconImage(ImageReader.getIcon("hulu.png").getImage());
        setLayout(null);
        setSize(width, height);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new HMWindowListener());

        welcome.setBounds(0, 0, width, height);
        getContentPane().add(welcome);
        setVisible(true);
        System.out.println("Welcome: " + welcome.getWidth());
        System.out.println("Welcome: " + welcome.getHeight());

        welcome_enter.addActionListener(e -> {
            setJMenuBar(menuBar);
            welcome.setVisible(false);

            ground.setBounds(0, 0, 960, 720);
            add(ground);
            control.setBounds(960, 0, 240, 102);
            add(control);
            status.setBounds(960, 102, 240, 618);
            add(status);

            setVisible(true);
            System.out.println("Ground: " + ground.getWidth());
            System.out.println("Ground: " + ground.getHeight());
        });

        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        validate();
    }
}

class HMWindowListener implements WindowListener {

    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent e) {}

    @Override
    public void windowClosed(WindowEvent e) {
        ArchiveIO.setRecordNo();
        System.out.println("saved recordNo: " + recordNo);
        System.out.println("Saved!");
        System.exit(0);
    }

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}
}