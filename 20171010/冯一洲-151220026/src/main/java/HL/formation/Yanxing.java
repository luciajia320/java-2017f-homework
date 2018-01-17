package HL.formation;
import HL.creature.Creature;
import HL.Field;

import java.util.ArrayList;


public class Yanxing extends Formation {

    public Yanxing(Field field, ArrayList<Creature> creatures, int baseline) {
         super(field, creatures, baseline);

    }
    public void formation(){
        System.out.println("雁形展开！");
        for(int i = 0; i<creatures.size();i++) {
            field.setCreature(creatures.get(i),i, baseline-i);
        }

    }
}
