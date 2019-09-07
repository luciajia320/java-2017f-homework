public class SnakeWoman implements Creature{
    final public static String SNAKEWOMAN = "\uD83D\uDC0D";
    Position pos;
    public String report()
    {
        return SNAKEWOMAN;
    }
    public void setposition(Position pos)
    {
        this.pos = pos;
    }
    public Position getposition()
    {
        return this.pos;
    }
}
