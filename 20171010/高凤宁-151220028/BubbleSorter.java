public class BubbleSorter implements Sorter {
    @Override
    public void sort(Queue queue) {
        Creature creature;
        Position[] positions = queue.GetPositions();

        for (int i = 0; i < positions.length - 1; i++) {
            for (int j = 0; j < positions.length - 1 - i; j++) {
                if (((Comparable) (positions[j].GetHolder())).biggerThan((Comparable) (positions[j + 1].GetHolder()))) {
                    creature = positions[j].GetHolder();
                    positions[j].SetHolder(positions[j + 1].GetHolder());
                    positions[j + 1].SetHolder(creature);
                }
            }
        }
    }
}
