package wz.form;

import wz.Creature.Creature;
import wz.Creature.Comparable;
import wz.position.Position;
import wz.position.RectField;

public class BubbleSort implements Sorter {
    @Override
    public void sort(RectField field, Creature[] creatures, int row, int col) {
        Position[][] positions = field.getPositions();
        for(int i = 0; i < creatures.length - 1; i++) {
            for(int j = 0; j < creatures.length - 1 - i; j++) {
                if(((Comparable)(positions[row + j][col].getHolder())).compareTo(
                        (Comparable)positions[row + 1 + j][col].getHolder())) {
                    Creature c = positions[row + 1 + j][col].getHolder();
                    positions[row + 1 + j][col].setHolder(positions[row + j][col].getHolder());
                    positions[row + j][col].setHolder(c);
                }
            }
        }
    }
}
