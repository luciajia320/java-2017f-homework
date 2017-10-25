public class changshe implements formation{
    public void sort(Map queue) {
        creature creature;
        Position[][] positions = queue.getPositions();

        for (int i = 0; i < 6; i++) {
            for (int j = 8; j < 14 - i; j++) {
                if (((brothers) (positions[j][3].getHolder())).biggerThan((brothers) (positions[j + 1][3].getHolder()))) {
                    creature = positions[j+1][3].getHolder();
                    positions[j + 1][3].setHolder(positions[j][3].getHolder());
                    positions[j][3].setHolder(creature);
                }
            }
        }
    }
}
