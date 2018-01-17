package nju.wz.form;

import nju.wz.creature.Creature;
import nju.wz.creature.Vacancy;
import nju.wz.position.RectField;

public class FormHeyi implements Form {
    @Override
    public void put(Creature[] creatures, int row, int col, RectField field) {
        int n = creatures.length;
        for(int i = 1; i <= n / 2; ++i) {
            field.addCreature(creatures[i], row + (i - 1), col - (i - 1));
        }
        field.addCreature(creatures[0], row + n / 2, col - n / 2);
        for(int i = n / 2 + 1; i < n; ++i) {
            field.addCreature(creatures[i], row + i, col - (n - i - 1));
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
            field.addCreature(new Vacancy(), row + length - i - 1, col + i);
        }
    }
}
