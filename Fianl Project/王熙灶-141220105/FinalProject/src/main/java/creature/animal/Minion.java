package creature.animal;

import util.ImageReader;

/**
 * 小喽啰类，继承自Creature类
 */
public class Minion extends Animal {
    public Minion() {
        imageAlive = ImageReader.getImage("minion-alive.png");
        imageDead = ImageReader.getImage("minion-dead.png");
    }

    @Override
    public String toString() {
        return "\uD83D\uDC12";
    }
}