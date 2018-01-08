import ui.HuluMountainFrame;
import util.ArchiveIO;
import util.Constant;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            JFrame.setDefaultLookAndFeelDecorated(true);
            UIManager.put("Menu.font", new Font("Microsoft YaHei UI", Font.PLAIN, 12));
            Locale.setDefault(Locale.ENGLISH);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        Constant.recordNo = ArchiveIO.getRecordNo();
        Constant.frame = new HuluMountainFrame();
    }
}