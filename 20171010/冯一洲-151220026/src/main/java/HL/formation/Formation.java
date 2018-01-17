package HL.formation;

import HL.Field;
import HL.creature.Creature;

import java.util.ArrayList;

public abstract class Formation {
    protected Field field;
    protected ArrayList<Creature> creatures = new ArrayList<Creature>();
    protected int baseline;

    public Formation(Field field, ArrayList<Creature>creatures, int baseline){
        this.field = field;
        this.creatures = creatures;
        this.baseline = baseline;
    }

    abstract void formation();
}
