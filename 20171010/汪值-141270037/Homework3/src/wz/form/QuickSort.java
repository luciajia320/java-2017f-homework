package wz.form;

import wz.Creature.Comparable;
import wz.Creature.Creature;
import wz.position.Position;
import wz.position.RectField;

public class QuickSort implements Sorter {
    @Override
    public void sort(RectField field, Creature[] creatures, int h, int l) {
        Position[][] positions = field.getPositions();
        for(int i = 0; i < creatures.length - 1; i++) {
            for(int j = 0; j < creatures.length - 1 - i; j++) {
                if(((Comparable)(positions[h][l].getHolder())).compareTo(
                        (Comparable)positions[h + 1][j + l].getHolder())) {
                    Creature c = positions[h + 1][l + j].getHolder();
                    positions[h + 1][l + j].setHolder(positions[h][l + j].getHolder());
                    positions[h][l + j].setHolder(c);
                }
            }
        }
    }
}
