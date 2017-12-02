import java.util.ArrayList;

public class BubbleSort implements Sorter{
    @Override
    public void sort(Queue queue) {
        Creature temp;
        ArrayList<Position> positions = queue.getPositions();

        for (int i = 0; i < positions.size() - 1; i++) {
            for (int j = 0; j < positions.size() - 1 - i; j++) {
                if (((Comparable) (positions.get(j).getHolder())).biggerThan((Comparable) (positions.get(j+1).getHolder()))) {
                	temp = positions.get(j).getHolder();
                	positions.get(j).setHolder(positions.get(j+1).getHolder());
                	positions.get(j+1).setHolder(temp);
                }
            }
        }
    }
}
