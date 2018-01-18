import java.util.ArrayList;

public class BubbleSorter implements Sorter {
    @Override
    public void sort(Queue queue) {
        Creature creature;
        ArrayList<Position<Creature>> positions= queue.getPositions();

        for (int i = 0; i < positions.size() - 1; i++) {
            for (int j = 0; j < positions.size() - 1 - i; j++) {
                if (((Comparable) (positions.get(j).getHolder())).biggerThan((Comparable) (positions.get(j+1).getHolder()))) {
                    creature = positions.get(j).getHolder();
                    positions.get(j + 1).getHolder().setPosition(positions.get(j));
                    creature.setPosition(positions.get(j + 1));
                }
            }
        }
    }
}
