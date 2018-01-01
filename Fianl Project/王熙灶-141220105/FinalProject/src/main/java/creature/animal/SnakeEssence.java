package creature.animal;

import util.ImageReader;

/**
 * 蛇精类，继承自Animal类
 */
public final class SnakeEssence extends Animal {
    private static SnakeEssence snakeEssence = new SnakeEssence();

    private SnakeEssence() {
        imageAlive = ImageReader.getImage("snake-alive.png");
        imageDead = ImageReader.getImage("snake-dead.png");
    }

    public static SnakeEssence getInstance() {
        return snakeEssence;
    }

    @Override
    public String toString() {
        return "\uD83D\uDC0D";
    }
}