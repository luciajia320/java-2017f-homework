
public class Sorter {
    public void sort(Queue queue, SORT_TYPE sortType) {
        sort(queue.getPositions(), sortType);
    }

    private void sort(Position[] positions, SORT_TYPE sortType) {
        switch (sortType) {
            case BUBBLE:
                bubbleSort(positions);
                break;
            case INSERTION:
                insertionSort(positions);
                break;
        }
    }

    private void bubbleSort(Position[] positions) {
        Creature temp;
        for (int i = 0; i < positions.length - 1; i ++) {
            for (int j = 0; j < positions.length - 1 - i; j ++) {
                if (((Comparable)(positions[j].getHolder())).biggerThan(
                        ((Comparable)(positions[j + 1].getHolder()))
                )) {
                    temp = positions[j].getHolder();
                    positions[j + 1].getHolder().setPosition(positions[j]);
                    temp.setPosition(positions[j + 1]);
                }
            }
        }
    }

    private void insertionSort(Position[] positions) {
        Creature temp;
        for (int i = 1; i < positions.length; i ++) {
            for (int j = i; j > 0; j --) {
                if (!((Comparable)(positions[j].getHolder())).biggerThan(
                        ((Comparable)(positions[j - 1].getHolder()))
                )) {
                    temp = positions[j].getHolder();
                    positions[j - 1].getHolder().setPosition(positions[j]);
                    temp.setPosition(positions[j - 1]);
                }
            }
        }
    }
}

enum SORT_TYPE {
    BUBBLE, INSERTION
}