public class InsertionSorter implements Sorter {
    @Override
    public void sort(Creature[] creatures) {
        Position[] positions=new Position[creatures.length];

        for (int i=0;i<creatures.length;i++)
        {
            positions[i]=creatures[i].getPosition();
        }

        Creature creature = null;
        int j;
        for (int i = 1; i < positions.length; i++) {
            for (j = i; j > 0; j--) {
                if (!((Comparable) (positions[j].getHolder())).biggerThan((Comparable) (positions[j - 1].getHolder()))) {
                    creature = positions[j].getHolder();
                    positions[j - 1].getHolder().setPosition(positions[j]);
                    creature.setPosition(positions[j - 1]);
                }
            }
        }
    }
}