package ui;

import util.ImageReader;

import javax.swing.*;
import java.awt.*;

import static util.Constant.*;

public class StatusBar extends JPanel {
    private Image table = ImageReader.getImage("table.jpg");

    /**
     * WIDTH = 240
     * HEIGHT = 618
     */
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
    public synchronized void paint(Graphics g) {
        super.paint(g);
        g.drawImage(table, 0, 58, 240, 560, this);
        g.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));

        int position_x = 90;
        int state_x = 180;

        // 蝎子精
        g.drawString(scorpion.getPosition().toString(), position_x, 110);
        g.drawString(scorpion.getCurrentState().toString(), state_x, 110);
        // 蛇精
        g.drawString(snake.getPosition().toString(), position_x, 145);
        g.drawString(snake.getCurrentState().toString(), state_x, 145);
        // 喽啰A
        g.drawString(minionA.getPosition().toString(), position_x, 175);
        g.drawString(minionA.getCurrentState().toString(), state_x, 175);
        // 喽啰B
        g.drawString(minionB.getPosition().toString(), position_x, 210);
        g.drawString(minionB.getCurrentState().toString(), state_x, 210);
        // 喽啰C
        g.drawString(minionC.getPosition().toString(), position_x, 245);
        g.drawString(minionC.getCurrentState().toString(), state_x, 245);
        // 喽啰D
        g.drawString(minionD.getPosition().toString(), position_x, 275);
        g.drawString(minionD.getCurrentState().toString(), state_x, 275);
        // 喽啰E
        g.drawString(minionE.getPosition().toString(), position_x, 310);
        g.drawString(minionE.getCurrentState().toString(), state_x, 310);
        // 喽啰F
        g.drawString(minionF.getPosition().toString(), position_x, 345);
        g.drawString(minionF.getCurrentState().toString(), state_x, 345);

        // 爷爷
        g.drawString(grandpa.getPosition().toString(), position_x, 375);
        g.drawString(grandpa.getCurrentState().toString(), state_x, 375);
        // 大娃
        g.drawString(calaA.getPosition().toString(), position_x, 410);
        g.drawString(calaA.getCurrentState().toString(), state_x, 410);
        // 二娃
        g.drawString(calaB.getPosition().toString(), position_x, 445);
        g.drawString(calaB.getCurrentState().toString(), state_x, 445);
        // 三娃
        g.drawString(calaC.getPosition().toString(), position_x, 475);
        g.drawString(calaC.getCurrentState().toString(), state_x, 475);
        // 四娃
        g.drawString(calaD.getPosition().toString(), position_x, 510);
        g.drawString(calaD.getCurrentState().toString(), state_x, 510);
        // 五娃
        g.drawString(calaE.getPosition().toString(), position_x, 545);
        g.drawString(calaE.getCurrentState().toString(), state_x, 545);
        // 六娃
        g.drawString(calaF.getPosition().toString(), position_x, 575);
        g.drawString(calaF.getCurrentState().toString(), state_x, 575);
        // 七娃
        g.drawString(calaG.getPosition().toString(), position_x, 610);
        g.drawString(calaG.getCurrentState().toString(), state_x, 610);
    }
}