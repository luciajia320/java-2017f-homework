package BattleGround;

import Creatures.Creature;
import Position.PositionInterface;

import java.util.List;

abstract public class BattleGround {
    protected PositionInterface[] positionInterfaces;
    protected List<Creature> creatures;

    public PositionInterface[] getPositionInterfaces() {
        return positionInterfaces;
    }
    public List<Creature> getCreatures() {
        return creatures;
    }

    public void addCreatures(Creature ... creatures) {
        for(Creature each:creatures)
            this.creatures.add(each);
    }

    public boolean deleteCreature(Creature target) {
        return creatures.remove(target);
    }

    public Creature getCreatureInPosition (PositionInterface pos){
        return pos.getHolder();
    }

    //view the battleground
    abstract void show();

    public abstract void initCreature();

    //the battle processing
    public abstract void battle();
}
