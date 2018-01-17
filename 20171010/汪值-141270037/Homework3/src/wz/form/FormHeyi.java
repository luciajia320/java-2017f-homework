package wz.form;

import wz.Creature.Creature;
import wz.Creature.Vacancy;
import wz.position.RectField;

public class FormHeyi implements Form{
    @Override
    public void put(Creature[] creatures, int row, int col, RectField field) {
        int length = creatures.length;
        for(int i = 0; i < length / 2; ++i) {
            field.addCreature(creatures[i], row + i, col + i);
        }
        field.addCreature(creatures[length/2], row + length / 2, col + length / 2);
        for(int i = length / 2 + 1; i < length; ++i) {
            field.addCreature(creatures[i], row + length -i-1, col + i);
        }
    }

    @Override
    public void reset(Creature[] creatures, int row, int col, RectField field) {
        int length = creatures.length;
        for(int i = 0; i < length / 2; ++i) {
            field.addCreature(new Vacancy(), row + i, col + i);
        }
        field.addCreature(new Vacancy(), row + length / 2, col + length / 2);
        for(int i = length / 2 + 1; i < length; ++i) {
            field.addCreature(new Vacancy(), row + length -i-1, col + i);
        }
    }
}
