package UI;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

//获得图像的工具类
public class BattleImage {
    private BattleImage() {};
    public static Image getImage(String path) {
        URL loc = BattleImage.class.getClassLoader().getResource(path);
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        return image;
    }
}
