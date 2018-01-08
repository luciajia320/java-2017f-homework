package HL.formation;
import HL.creature.Creature;
import HL.Field;

public class HeYi extends  Formation {

    public HeYi(Field field, Creature[] creatures, int baseline) {
        int height = (creatures.length - 1)/2;
        field.setCreature(creatures[0],height , baseline);
        for(int i = 1; i<=height;i++)
        {
            field.setCreature(creatures[i],height - i, baseline - i);
            field.setCreature(creatures[i],height - i, baseline + i);
        }
    }
}
