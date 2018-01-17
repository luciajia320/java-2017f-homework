package nju.java;


import nju.java.common.Constant;

import javax.swing.*;
import java.awt.*;


public final class Ground extends JFrame{

    private final int OFFSET = 30;

    public Ground() {
        InitUI();
    }

    public void InitUI() {
        WelcomePage();
        Field field = new Field();
        add(field);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Constant.DEFAULT_LAYOUT_WIDTH + OFFSET/2,
                 Constant.DEFAULT_LAYOUT_HEIGHT+ 6 * OFFSET);
        setLocationRelativeTo(null);
        setTitle("Ground");

    }

    public void WelcomePage(){
        JPanel welcome_page = new JPanel();
        String option_txt = "<html> 开始游戏：空格键<br/>" +
                " 读取存档：L键<br/>" +
                "退出游戏：Esc<br/>"+
                "重新开始：R<br/>"+
                "<br/></html>";

        JLabel textLabel = new JLabel(option_txt);
        textLabel.setFont(new Font("宋体",Font.BOLD, 24));
        textLabel.setForeground( Color.BLACK);
        welcome_page.add(textLabel);
        this.add(welcome_page, BorderLayout.SOUTH);
    }

}