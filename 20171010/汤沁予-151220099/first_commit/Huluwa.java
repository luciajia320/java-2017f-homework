public class Huluwa implements Creature,Comparable
{
    private Position pos;
    private Color color;
    private int rank;
    @Override
    public String report()
    {
        return this.color.toString();
    }
    @Override
    public void setposition(Position pos)
    {
        this.pos = pos;
    }
    @Override
    public Position getposition()
    {
        return this.pos;
    }

    @Override
    public boolean biggerthan(Comparable anathor)
    {
        if(anathor instanceof Huluwa)
        {
            return ((Huluwa) anathor).GetRank() < this.GetRank();
        }
        else
        {
            System.out.print("Comparing Creature Huluwa to other objects.error.\n");
            return false;
        }
    }

    int GetRank() {return rank;}

    Huluwa(Color color,int rank)
    {
        this.color = color;
        this.rank = rank;
        this.pos = null;
    }
}

enum Color
{
    红,橙,黄,绿,青,蓝,紫;
}
