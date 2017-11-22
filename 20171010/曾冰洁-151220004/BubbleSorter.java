import java.util.ArrayList;

public class BubbleSorter implements Sorter {
    @Override
    public void sort(Queue queue) {
        ArrayList<Position> copyPositions = queue.getPositions();
        for(int i = 0; i < copyPositions.size(); i++) {
            boolean exchangeHappened = false;
            for (int j = 0; j < copyPositions.size() - 1 - i; j++) {
                if (((Comparable) (copyPositions.get(j).getHolder())).isYoungerThan((Comparable) (copyPositions.get(j+1).getHolder()))) {
                    exchangeHappened = true;
                    copyPositions.get(j).swapHolderWith(copyPositions.get(j+1));
                }
            }
            if (!exchangeHappened) { break; }
        }
    }
}
