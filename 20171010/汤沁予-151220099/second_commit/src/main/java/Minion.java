public class Minion implements Creature
{
    final public static String MINION = "\uD83D\uDE08";
    private Position pos;
    public String report()
    {
        return MINION;
    }
    public void setposition(Position pos)
    {
        this.pos = pos;
    }
    public Position getposition()
    {
        return pos;
    }
}
