public class Grandpa implements Creature
{
    private Position pos;
    final static public String GRANDPA = "\uD83D\uDC74";
    public String report()
    {
        return GRANDPA;
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
