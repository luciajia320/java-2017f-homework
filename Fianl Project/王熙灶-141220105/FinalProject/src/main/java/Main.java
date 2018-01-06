import ui.HuluMountainFrame;
import util.Constant;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            JFrame.setDefaultLookAndFeelDecorated(true);
            UIManager.put("Menu.font", new Font("Microsoft YaHei UI", Font.PLAIN, 12));
//            UIManager.put("Button.font", new Font("Microsoft YaHei UI", Font.PLAIN, 10));
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        Constant.frame = new HuluMountainFrame();
    }
}