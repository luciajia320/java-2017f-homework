public class SingleCreatureFormat extends Format
{
    Creature creature;
    SingleCreatureFormat(Creature creature,int x,int y)
    {
        super(1,1,x,y);
        this.creature = creature;
        PutCreatures();
    }
    @Override
    public void PutCreatures()
    {
        super.getGround()[0][0].setHolder(this.creature);
        this.creature.setposition(super.getGround()[0][0]);
    }
}

