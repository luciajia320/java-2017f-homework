package HL.formation;
import HL.creature.Creature;
import HL.Field;


public class Yanxing extends Formation {

    public Yanxing(Field field, Creature[] creatures, int baseline) {
        for(int i = 0; i<creatures.length;i++)
        {
            field.setCreature(creatures[i],i, baseline-i);
        }
    }
}
