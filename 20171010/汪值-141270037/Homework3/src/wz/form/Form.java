package wz.form;

import wz.Creature.Creature;
import wz.position.RectField;

public interface Form {
    public void put(Creature[] creatures, int row, int col, RectField field);
    public void reset(Creature[] creatures, int row, int col, RectField field);
}
