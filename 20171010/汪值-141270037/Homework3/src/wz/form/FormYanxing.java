package wz.form;

import wz.Creature.Creature;
import wz.Creature.Vacancy;
import wz.position.RectField;

public class FormYanxing implements Form{
    @Override
    public void put(Creature[] creatures, int row, int col, RectField field) {
        for(int i = 0; i < creatures.length; ++i){
            field.addCreature(creatures[i], row +i, col+i);
        }
    }

    @Override
    public void reset(Creature[] creatures, int row, int col, RectField field) {
        for(int i = 0; i < creatures.length; ++i){
            field.addCreature(new Vacancy(), row+i, col+i);
        }
    }



}
