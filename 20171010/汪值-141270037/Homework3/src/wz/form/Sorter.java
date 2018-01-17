package wz.form;

import wz.Creature.Creature;
import wz.position.RectField;

public interface Sorter {
    public void sort(RectField field, Creature[] creatures, int h, int l);
}
