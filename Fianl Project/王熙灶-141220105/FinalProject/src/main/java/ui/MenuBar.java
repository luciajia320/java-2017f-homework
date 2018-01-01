package ui;

import javax.swing.*;
import java.awt.event.InputEvent;

public class MenuBar extends JMenuBar {
    public MenuBar() {
        JMenu filemenu=new JMenu("File");
        filemenu.setMnemonic('F');
        JMenuItem newitem=new JMenuItem("New");
//        newitem.addActionListener(new newListener());
        newitem.setAccelerator(KeyStroke.getKeyStroke('N', InputEvent.CTRL_MASK,false));
        JMenuItem openitem=new JMenuItem("Open");
//        openitem.addActionListener(new openListener());
        openitem.setAccelerator(KeyStroke.getKeyStroke('O',InputEvent.CTRL_MASK,false));
        JMenuItem saveitem=new JMenuItem("Save");
//        saveitem.addActionListener(new saveListener());
        saveitem.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_MASK,false));
        JMenuItem closeitem=new JMenuItem("Close");
//        closeitem.addActionListener(new closeListener());
        closeitem.setAccelerator(KeyStroke.getKeyStroke('Q', InputEvent.CTRL_MASK,false));
        filemenu.add(openitem);
        filemenu.addSeparator();
        filemenu.add(saveitem);
        filemenu.addSeparator();
        filemenu.add(closeitem);
        add(filemenu);
    }
}
