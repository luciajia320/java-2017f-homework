package roles;

public class Snake implements Creature {
    public final String name = "蛇精";

    private Snake() { }

    private static Snake snake = new Snake();

    public static Snake getSnake()
    {
        return snake;
    }

}
