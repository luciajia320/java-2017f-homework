package creature;

/**
 * 蛇精类，继承自Creature类
 */
public final class SnakeEssence extends Creature {
    private static SnakeEssence snakeEssence = new SnakeEssence();

    private SnakeEssence() {}

    public static SnakeEssence getInstance() {
        return snakeEssence;
    }

    @Override
    public String toString() {
        return "蛇";
    }
}