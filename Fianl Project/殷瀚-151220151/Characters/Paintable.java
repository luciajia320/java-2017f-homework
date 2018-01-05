package Characters;

import java.awt.*;

public interface Paintable {
    public Image getImage();
    void paintInGraphics(Graphics g, int positionWidth, int positionHeight);
}
