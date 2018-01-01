package util;

import javax.imageio.ImageIO;
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
}