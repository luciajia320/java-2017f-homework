import java.util.ArrayList;

public class InsertionSorter implements Sorter {
    @Override
    public void sort(Field queue,int row) {
        ArrayList<Position> positions = queue.getPositions(row);
        Creature creature = null;
        int j;
        for (int i = 5; i < 10; i++) {
            for (j = i; j > 4; j--) {
                if (!((Comparable) (positions.get(j).getHolder())).biggerThan((Comparable) (positions.get(j-1).getHolder()))) {
                    creature = positions.get(j).getHolder();
                    positions.get(j-1).getHolder().setPosition(positions.get(j));
                    creature.setPosition(positions.get(j-1));
                }
            }
        }
    }
}
