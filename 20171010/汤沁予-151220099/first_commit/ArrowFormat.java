public class ArrowFormat extends Format
{
    Creature[] objs;
    ArrowFormat(Creature[] creatures,int x0,int y0)
    {
        super(3,5,x0,y0);
        objs = creatures;
        PutCreatures();
    }
    @Override
    public void PutCreatures()
    {
        super.PutACreature((Creature)objs[0],super.getGround()[0][2]);
        super.PutACreature((Creature)objs[1],super.getGround()[1][1]);
        super.PutACreature((Creature)objs[2],super.getGround()[1][2]);
        super.PutACreature((Creature)objs[3],super.getGround()[1][3]);
        super.PutACreature((Creature)objs[4],super.getGround()[2][0]);
        super.PutACreature((Creature)objs[5],super.getGround()[2][2]);
        super.PutACreature((Creature)objs[6],super.getGround()[2][4]);
    }
}

