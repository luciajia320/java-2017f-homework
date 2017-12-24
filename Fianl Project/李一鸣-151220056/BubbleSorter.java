import java.util.ArrayList;

public class BubbleSorter implements Sorter {
    @Override
    public void sort(Field queue ,int row) {
        Creature creature;
        ArrayList<Position> positions = queue.getPositions(row);

        for (int i = 4; i < 10; i++) {
            for (int j = 4; j < 10; j++) {
                /*
                if (((Comparable) (positions.get[j].getHolder())).biggerThan((Comparable) (positions[j + 1].getHolder()))) {
                    creature = positions[j].getHolder();
                    positions[j + 1].getHolder().setPosition(positions[j]);
                    creature.setPosition(positions[j + 1]);
                }*/
                if (((Comparable) (positions.get(j).getHolder())).biggerThan((Comparable) (positions.get(j+1).getHolder()))) {
                    creature = positions.get(j).getHolder();
                    positions.get(j+1).getHolder().setPosition(positions.get(j));
                    creature.setPosition(positions.get(j+1));
                }
            }
        }
    }
}

