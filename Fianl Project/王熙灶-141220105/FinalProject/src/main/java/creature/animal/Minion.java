package creature.animal;

import util.ImageReader;

/**
 * 小喽啰类，继承自Creature类
 */
public class Minion extends Animal {
    public Minion() {
        imageAlivePath = "minion-alive.png";
        imageDeadPath = "minion-dead.png";
        imageAlive = ImageReader.getImage(imageAlivePath);
        imageDead = ImageReader.getImage(imageDeadPath);
    }

    @Override
    public String toString() {
        return "\uD83D\uDC12";
    }
}