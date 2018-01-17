package ui;

import javax.swing.*;
import java.awt.*;

public class Images {
    public static Image getImage(String path) {
        ImageIcon icon = new ImageIcon(path);
        return icon.getImage();
    }
}
