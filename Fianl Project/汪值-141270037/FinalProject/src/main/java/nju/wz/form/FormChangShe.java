package nju.wz.form;

import nju.wz.creature.Creature;
import nju.wz.creature.Vacancy;
import nju.wz.position.RectField;

public class FormChangShe implements Form{

    @Override
    public void put(Creature[] creatures, int row, int col, RectField field) {
        for(int i = 0; i < creatures.length; ++i){
            field.addCreature(creatures[i], row +i, col);
        }
    }

    @Override
    public void reset(Creature[] creatures, int row, int col, RectField field) {
        for(int i = 0; i < creatures.length; ++i){
            field.addCreature(new Vacancy(), row+i, col);
        }
    }
}
