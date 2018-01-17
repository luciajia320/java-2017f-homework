package creature.animal;

import util.ImageReader;

/**
 * 蛇精类，继承自Animal类
 */
public final class SnakeEssence extends Animal {
    private static SnakeEssence snakeEssence = new SnakeEssence();

    private SnakeEssence() {
        imageAlivePath = "snake-alive.png";
        imageDeadPath = "snake-dead.png";
        imageAlive = ImageReader.getImage(imageAlivePath);
        imageDead = ImageReader.getImage(imageDeadPath);
    }

    public static SnakeEssence getInstance() {
        return snakeEssence;
    }

    @Override
    public String toString() {
        return "\uD83D\uDC0D";
    }
}