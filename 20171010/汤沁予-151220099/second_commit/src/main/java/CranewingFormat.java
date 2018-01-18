//import java.lang.Math.*
public class CranewingFormat extends Format
{
    Creature[] objs;
    CranewingFormat(Creature[] creatures,int x0,int y0)
    {
        super(4,7,x0,y0);
        objs = creatures;
        PutCreatures();
    }
    @Override
    public void PutCreatures()
    {
        for(int i = 0;i<objs.length;i++)
            super.PutACreature((Creature)objs[i],super.getGround()[3-Math.abs(i-3)][i]);
    }
}
