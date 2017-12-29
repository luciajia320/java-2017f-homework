package ui;

import common.Constant;
import entity.Ground;

import javax.swing.*;
import java.io.IOException;

public class Menu extends JMenuBar {
    // 菜单栏
    private JMenu m1 = null, m2 = null, m3 = null;
    // 菜单项
    private JMenuItem mi11 = null, mi12 = null, mi21 = null, mi22 = null, mi3 = null;

    private MainFrame mf = null;

    // 菜单栏和菜单项
    public Menu(MainFrame mf) {
        this.mf = mf;

        m1 = new JMenu("游戏");
        m2 = new JMenu("暂停/继续");
        m3 = new JMenu("帮助");

        mi11 = new JMenuItem("开始游戏");
        mi12 = new JMenuItem("退出");
        mi21 = new JMenuItem("暂停");
        mi22 = new JMenuItem("继续");
        mi3 = new JMenuItem("游戏说明");

        m1.add(mi11); m1.add(mi12);
        m2.add(mi21); m2.add(mi22);
        m3.add(mi3);

        Ground ground = mf.getGround();

        /* lambda表达式实现actionlistener */
        mi11.addActionListener(e -> {   // start
            ground.stopAll();
            Object[] options = {"确定", "取消"};
            int response = JOptionPane.showOptionDialog(mf, "确定开始新游戏?", "", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (response == 0) {
                mf.dispose();
                mf.newSave();
                ground.restart();
                mf.setVisible(true);
            } else {
                if (ground.getState() == Constant.STATE.GOING) ground.moveAll();
            }
        });
        mi12.addActionListener(e -> {   // exit
            ground.stopAll();
            Object[] options = {"确定", "取消"};
            int response = JOptionPane.showOptionDialog(mf, "确定退出游戏?", "", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (response == 0) {
                if (mf.fw != null) try {
                    mf.fw.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
                System.exit(0);
            } else {
                if (ground.getState() == Constant.STATE.GOING) ground.moveAll();
            }
        });
        mi21.addActionListener(e -> {   // pause
            ground.setState(Constant.STATE.PAUSE);
            ground.stopAll();
        });
        mi22.addActionListener(e -> {   // continue
            ground.moveAll();
        });
        mi3.addActionListener(e -> {    // help
            ground.setState(Constant.STATE.PAUSE);
            ground.stopAll();
            JOptionPane.showMessageDialog(mf, "按空格键控制双方交战", "提示!", JOptionPane.INFORMATION_MESSAGE);
            this.setVisible(true);
            ground.moveAll();
        });

        add(m1); add(m2); add(m3);
    }
}
