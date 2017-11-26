import java.util.ArrayList;

public class changshe implements layout{
    @Override
    public void sort(Map queue) {
        creature creature;
        ArrayList<ArrayList<Position>> positions = queue.getPositions();

        for (int i = 0; i < 6; i++) {
            for (int j = 6; j < 12 - i; j++) {
                if (((brothers) (positions.get(j).get(3).getHolder())).biggerThan((brothers) (positions.get(j+1).get(3).getHolder()))) {
                    creature = positions.get(j+1).get(3).getHolder();
                    positions.get(j+1).get(3).setHolder(positions.get(j).get(3).getHolder());
                    positions.get(j).get(3).setHolder(creature);
                }
            }
        }
    }
}
