package ui;

import archive.TimePoint;
import util.ArchiveIO;
import util.Constant;
import util.ImageReader;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MenuBar extends JMenuBar {
    private AboutFrame aboutFrame = new AboutFrame();
    private HelpFrame helpFrame = new HelpFrame();
    private JFileChooser fileChooser = new JFileChooser("src/main/resources/archives");

    public MenuBar() {
        final List<TimePoint> times = new ArrayList<>();

        fileChooser.setFileFilter(new ArchiveFilter());
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        JMenu filemenu = new JMenu("File");
        filemenu.setMnemonic('F');
        JMenuItem openitem = new JMenuItem("Open", ImageReader.getIcon("open.png"));
        openitem.addActionListener((ActionEvent e) -> {
            fileChooser.setApproveButtonText("确定");
            fileChooser.setDialogTitle("打开文件");
            int result = fileChooser.showOpenDialog(Constant.frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                System.out.println("您选择打开的文件名称为：" + file.getName());
                times.addAll(ArchiveIO.read(file.getName()));
                System.out.println(times);
            } else if (result == JFileChooser.CANCEL_OPTION) {
                System.out.println("您没有选择任何文件");
            }
        });

        openitem.setAccelerator(KeyStroke.getKeyStroke('O',InputEvent.CTRL_MASK,false));

        JMenuItem saveitem = new JMenuItem("Save", ImageReader.getIcon("save.png"));
        saveitem.addActionListener((ActionEvent e) -> {
            fileChooser.setApproveButtonText("确定");
            fileChooser.setDialogTitle("保存文件");

            int result = fileChooser.showSaveDialog(Constant.frame);
            while (true) {
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    if (file.exists()) {
                        int copy = JOptionPane.showConfirmDialog(null, "Are U sure to overwrite " + file.getName() + "?", "Save", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (copy == JOptionPane.YES_OPTION) {
                            System.out.println("您选择保存的文件名称为：" + file.getName());
                            System.out.println(times);
                            ArchiveIO.write(times, file.getName());
                            break;
                        }
                        else {
                            result = fileChooser.showSaveDialog(Constant.frame);
                        }
                    }
                    else {
                        System.out.println("您选择保存的文件名称为：" + file.getName());
                        System.out.println(times);
                        ArchiveIO.write(times, file.getName());
                        break;
                    }
                } else if (result == JFileChooser.CANCEL_OPTION) {
                    System.out.println("您没有选择任何文件");
                    break;
                }
            }
        });
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

    class ArchiveFilter extends FileFilter {
        @Override
        public boolean accept(File f) {
            return f.getName().endsWith(".acv");
        }

        @Override
        public String getDescription() {
            return "Archive file (*.acv)";
        }
    }
}