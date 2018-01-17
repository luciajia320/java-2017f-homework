public class Position
{
    private int x;
    private int y;
    private Creature holder;

    public Creature getHolder()
    {
        return holder;
    }
    public void setHolder(Creature holder)
    {
        this.holder = holder;
    }
    public int getX()
    {
        return this.x;
    }
    public void setX(int x)
    {
        this.x = x;
    }
    public int getY()
    {
        return this.y;
    }
    public void setY(int y)
    {
        this.y = y;
    }

    public Position(int x,int y)
    {
        this.x = x;
        this.y = y;
        holder = null;
    }

}
