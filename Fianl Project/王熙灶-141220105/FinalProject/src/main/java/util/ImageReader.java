package util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImageReader {
    public static Image getImage(String path) {
        try {
            return ImageIO.read(new File("src/main/resources/images/" + path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ImageIcon getIcon(String path) {
        return new ImageIcon("src/main/resources/icons/" + path);
    }
}