package ui;

import util.ImageReader;

import javax.swing.*;
import java.awt.*;

public class StatusBar extends JPanel {
    private Image table = ImageReader.getImage("table.jpg");

    public StatusBar() {
        JLabel label = new JLabel("Status");
        label.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 28));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setBounds(0, 0, 240, 58);
        add(label);

        setBorder(BorderFactory.createEtchedBorder());
        setBackground(new Color(229, 237, 154));
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(table, 0, 58, 240, 560, this);
        g.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));

        // 蝎子精
        g.drawString("(10, 20)", 83, 110);
        g.drawString("对阵", 180, 110);
        // 蛇精
        g.drawString("(2, 20)", 83, 145);
        g.drawString("战斗", 180, 145);
        // 喽啰A
        g.drawString("(10, 20)", 83, 175);
        g.drawString("加油", 180, 175);
        // 喽啰B
        g.drawString("(2, 20)", 83, 210);
        g.drawString("死亡", 180, 210);
        // 喽啰C
        g.drawString("(2, 2)", 83, 245);
        g.drawString("冲锋", 180, 245);
        // 喽啰D
        g.drawString("(10, 20)", 83, 275);
        g.drawString("加油", 180, 275);
        // 喽啰E
        g.drawString("(2, 20)", 83, 310);
        g.drawString("加油", 180, 310);
        // 喽啰F
        g.drawString("(2, 2)", 83, 345);
        g.drawString("加油", 180, 345);
        // 爷爷
        g.drawString("(10, 20)", 83, 375);
        g.drawString("加油", 180, 375);
        // 大娃
        g.drawString("(2, 20)", 83, 410);
        g.drawString("加油", 180, 410);
        // 二娃
        g.drawString("(2, 2)", 83, 445);
        g.drawString("加油", 180, 445);
        // 三娃
        g.drawString("(10, 20)", 83, 475);
        g.drawString("加油", 180, 475);
        // 四娃
        g.drawString("(2, 20)", 83, 510);
        g.drawString("加油", 180, 510);
        // 五娃
        g.drawString("(2, 2)", 83, 545);
        g.drawString("加油", 180, 545);
        // 六娃
        g.drawString("(10, 20)", 83, 575);
        g.drawString("加油", 180, 575);
        // 七娃
        g.drawString("(2, 20)", 83, 610);
        g.drawString("加油", 180, 610);
    }
}
