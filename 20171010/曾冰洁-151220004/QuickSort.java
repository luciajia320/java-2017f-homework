import java.util.ArrayList;

public class QuickSort implements Sorter {
    public void sort(Queue queue){
        ArrayList<Position> copyPositions = queue.getPositions();
        quickSort(0, copyPositions.size()-1, copyPositions);
    }
    private int split(int left, int right, ArrayList<Position> positions) {
        int i = left;
        int j = right;
        Creature tmpCreature = positions.get(i).getHolder();
        while (i<j)
        {
            while (i<j && (((Comparable)(positions.get(j).getHolder())).isYoungerThan((Comparable)(tmpCreature))))
                j--;
            if (i<j) {
                positions.get(j).moveHolderTo(positions.get(i));
            }
            while (i<j && (((Comparable)(positions.get(i).getHolder())).isOlderThan((Comparable)(tmpCreature))))
                i++;
            if (i<j){
                positions.get(i).moveHolderTo(positions.get(j));
            }
        }
        tmpCreature.setPosition(positions.get(i));
        return i;
    }
    private void quickSort(int left, int right, ArrayList<Position> positions){
        int pivot;
        if (left<right)
        {
            pivot = split(left, right, positions);
            quickSort(left, pivot - 1, positions);
            quickSort(pivot + 1, right, positions);
        }
    }
}
