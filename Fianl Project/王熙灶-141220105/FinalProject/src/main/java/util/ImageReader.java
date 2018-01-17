package util;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ImageReader {
    public static Image getImage(String path) {
        URL url = ImageReader.class.getResource("/images/" + path);
        return new ImageIcon(url).getImage();
    }

    public static ImageIcon getIcon(String path) {
        URL url = ImageReader.class.getResource("/icons/" + path);
        return new ImageIcon(url);
    }
}