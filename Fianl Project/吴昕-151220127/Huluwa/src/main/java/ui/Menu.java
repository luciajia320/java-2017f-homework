package ui;

import common.Constant;
import entity.Ground;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

import static common.Constant.SAVEPATH;

public class Menu extends JMenuBar {
    // 菜单栏
    private JMenu m1 = null, m2 = null, m3 = null;
    // 菜单项
    private JMenuItem mi11 = null, mi12 = null, mi13 = null,  mi21 = null, mi22 = null, mi3 = null;

    private MainFrame mf = null;

    // 菜单栏和菜单项
    public Menu(MainFrame mf) {
        this.mf = mf;

        m1 = new JMenu("游戏");
        m2 = new JMenu("暂停/继续");
        m3 = new JMenu("帮助");

        mi11 = new JMenuItem("开始游戏");
        mi12 = new JMenuItem("退出");
        mi13 = new JMenuItem("查看存档");
        mi21 = new JMenuItem("暂停");
        mi22 = new JMenuItem("继续");
        mi3 = new JMenuItem("游戏说明");

        m1.add(mi11); m1.add(mi12); m1.add(mi13);
        m2.add(mi21); m2.add(mi22);
        m3.add(mi3);

        Ground ground = mf.getGround();

        /* lambda表达式实现actionlistener */
        mi11.addActionListener(e -> {   // start
            ground.start();
        });
        mi12.addActionListener(e -> {   // exit
            ground.exit();
        });
        mi13.addActionListener(e -> {   // save
            ground.readSave();
        });
        mi21.addActionListener(e -> {   // pause
            ground.pause();
        });
        mi22.addActionListener(e -> {   // continue
            ground.moveAll();
        });
        mi3.addActionListener(e -> {    // help
            ground.help();
        });

        add(m1); add(m2); add(m3);
    }
}
