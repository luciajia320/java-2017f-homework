package HL.formation;
import HL.creature.Creature;
import HL.Field;

import java.util.ArrayList;

public class HeYi extends  Formation {

    public HeYi(Field field, ArrayList<Creature> creatures, int baseline) {
        super(field, creatures, baseline);

    }

    public void formation(){
        System.out.println("鹤翼展开！");
        int height = (creatures.size() - 1)/2;
        field.setCreature(creatures.get(0),height , baseline);
        for(int i = 1; i<=height;i++)
        {
            field.setCreature(creatures.get(i),height - i, baseline - i);
            field.setCreature(creatures.get(i),height - i, baseline + i);
        }

    }
}
