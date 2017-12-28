import java.util.ArrayList;

public class BubbleSorter implements Sorter {
    @Override
    public void sort(Field queue ,int line) {
        Creature creature;
        ArrayList<Position> positions = queue.getPositions(line);
    //  System.out.println(positions.get(0).getX());
        for (int i = 0; i <7; i++) {
            for (int j = i+1; j <6; j++) {
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

