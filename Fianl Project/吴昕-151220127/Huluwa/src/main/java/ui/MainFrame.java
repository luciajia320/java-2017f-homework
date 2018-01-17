package ui;

import common.Constant;
import entity.Ground;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.security.Key;

public class MainFrame extends JFrame implements Constant {
    private static Ground ground = new Ground();
    public Ground getGround() { return ground; }

    public MainFrame() { // 构造函数
        setSize(FRAMEWIDTH, FRAMEHEIGHT);
        setLocation(LOCATIONX, LOCATIONY);
        setTitle(TITLE);
        setResizable(false);

        initFrame();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initFrame() {
        // 菜单
        setJMenuBar(new Menu(this));

        // 加入场地元素
        ground.setMainFrame(this);
        add(ground);
        thread = new Thread(ground);
        thread.start();

        // 键盘监听, 按空格开始行动
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // 所有线程启动
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (ground.getState() == STATE.READY || ground.getState() == STATE.PAUSE)
                        ground.moveAll();
                    else if (ground.getState() == STATE.SAVE) ground.replayAll();
                }
                else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    if (ground.getState() == STATE.SAVE) ground.replayOnce();
                }
                else if (e.getKeyCode() == KeyEvent.VK_L) {
                    if (ground.getState() == STATE.READY || ground.getState() == STATE.SAVE) {
                        JFileChooser jfc = new JFileChooser(SAVEPATH);
                        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                        int result = jfc.showOpenDialog(null);
                        if (result == JFileChooser.APPROVE_OPTION) {
                            File file = jfc.getSelectedFile();
                            ground.replay(file);
                        }
                        else ground.options("是否开启新游戏? ");
                    }
                }
                else if (e.getKeyCode() == KeyEvent.VK_P) {
                    if (ground.getState() == STATE.GOING) ground.pause();
                }
                else if (e.getKeyCode() == KeyEvent.VK_C) {
                    if (ground.getState() == STATE.PAUSE) ground.moveAll();
                }
                else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) ground.exit();
                else if (e.getKeyCode() == KeyEvent.VK_H) ground.help();
                else if (e.getKeyCode() == KeyEvent.VK_S) ground.readSave();
            }
        });

        // 存档
        newSave();
    }

    private static int saveNum = 0;
    private File file;
    public FileWriter fw;
    public Thread thread;

    public void newSave() {
        file = new File(SAVEPATH + String.valueOf(saveNum++));
        try {
            while (file.exists() == true)
                file = new File(SAVEPATH + String.valueOf(saveNum++));
            file.createNewFile();
//            System.out.println("create save doc: " + file.getName());
            fw = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}