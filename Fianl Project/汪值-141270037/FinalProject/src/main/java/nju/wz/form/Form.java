package nju.wz.form;

import nju.wz.creature.Creature;
import nju.wz.position.RectField;

public interface Form {
    public void put(Creature[] creatures, int row, int col, RectField field);
    public void reset(Creature[] creatures, int row, int col, RectField field);
}
