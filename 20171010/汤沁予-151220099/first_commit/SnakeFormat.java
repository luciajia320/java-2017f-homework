public class SnakeFormat extends Format
{
    Creature objs[];
    SnakeFormat(Creature[] creatures,int x0,int y0)
    {
        super(7,1,x0,y0);
        objs =  creatures;
    }
    @Override
    public void PutCreatures()
    {
        for(int i = 0;i<objs.length;i++)
            super.PutACreature((Creature)objs[i],super.getGround()[i][0]);
    }
}
