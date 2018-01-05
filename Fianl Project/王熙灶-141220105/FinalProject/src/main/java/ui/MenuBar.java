package ui;

import util.ImageReader;

import javax.swing.*;
import java.awt.event.InputEvent;

public class MenuBar extends JMenuBar {
    private AboutFrame aboutFrame = new AboutFrame();
    private HelpFrame helpFrame = new HelpFrame();

    public MenuBar() {
        JMenu filemenu=new JMenu("File");
        filemenu.setMnemonic('F');
        JMenuItem openitem = new JMenuItem("Open", ImageReader.getIcon("open.png"));
//        openitem.addActionListener(new openListener());
        openitem.setAccelerator(KeyStroke.getKeyStroke('O',InputEvent.CTRL_MASK,false));
        JMenuItem saveitem = new JMenuItem("Save", ImageReader.getIcon("save.png"));
//        saveitem.addActionListener(new saveListener());
        saveitem.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_MASK,false));
        JMenuItem closeitem = new JMenuItem("Close", ImageReader.getIcon("close.png"));
        closeitem.addActionListener(e -> System.exit(0));
        closeitem.setAccelerator(KeyStroke.getKeyStroke('Q', InputEvent.CTRL_MASK,false));

        filemenu.add(openitem);
        filemenu.addSeparator();
        filemenu.add(saveitem);
        filemenu.addSeparator();
        filemenu.add(closeitem);
        add(filemenu);

        JMenu runmenu = new JMenu("Run");
        runmenu.setMnemonic('R');
        JMenuItem startitem = new JMenuItem("Start", ImageReader.getIcon("start.png"));
//        newitem.addActionListener(new newListener());
        startitem.setAccelerator(KeyStroke.getKeyStroke('T', InputEvent.CTRL_MASK,false));
        JMenuItem stopitem = new JMenuItem("Stop", ImageReader.getIcon("stop.png"));
//        openitem.addActionListener(new openListener());
        stopitem.setAccelerator(KeyStroke.getKeyStroke('P',InputEvent.CTRL_MASK,false));
        JMenuItem resetitem = new JMenuItem("Reset", ImageReader.getIcon("reset.png"));
//        saveitem.addActionListener(new saveListener());
        resetitem.setAccelerator(KeyStroke.getKeyStroke('R', InputEvent.CTRL_MASK,false));

        runmenu.add(startitem);
        runmenu.addSeparator();
        runmenu.add(stopitem);
        runmenu.addSeparator();
        runmenu.add(resetitem);
        add(runmenu);

        JMenu helpmenu = new JMenu("Help");
        helpmenu.setMnemonic('H');
        JMenuItem helpitem = new JMenuItem("Help", ImageReader.getIcon("help.png"));
        helpitem.addActionListener(e -> {
            helpFrame.setVisible(true);
            System.out.println(helpFrame.getContentPane().getWidth());
            System.out.println(helpFrame.getContentPane().getHeight());
        });
        helpitem.setAccelerator(KeyStroke.getKeyStroke('H', InputEvent.CTRL_MASK,false));
        JMenuItem aboutitem = new JMenuItem("About", ImageReader.getIcon("about.png"));
        aboutitem.addActionListener(e -> {
            aboutFrame.setVisible(true);
            System.out.println(aboutFrame.getContentPane().getWidth());
            System.out.println(aboutFrame.getContentPane().getHeight());
        });
        aboutitem.setAccelerator(KeyStroke.getKeyStroke('A', InputEvent.CTRL_MASK,false));

        helpmenu.add(helpitem);
        helpmenu.addSeparator();
        helpmenu.add(aboutitem);
        add(helpmenu);
    }
}
