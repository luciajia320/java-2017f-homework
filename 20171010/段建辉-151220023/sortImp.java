import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.List;

class ranksort implements sort {

    @Override
    public void Sort(List<Position>[] positions, int start, int end) {
        for (int i = start; i < end; i++) {
            for (int j = start; j < end - 1; j++) {
                List<Position> temp = new ArrayList();
                if (positions[j].get(0).getCreature().getRank() > positions[j + 1].get(0).getCreature().getRank()){
                    temp =  positions[j];
                    positions[j] = positions[j + 1];
                    //System.out.println(creatures[j].getName() + ": " + (j + 1) + "->" + j);
                    positions[j + 1] = temp;
                    //System.out.println(creatures[j + 1].getName() + ": " + j + "->" + (j + 1));
                }
            }
        }
    }
}
/*
class colorsort implements sort {
    private int binarySearch(int start, int end, creature value, creature[] creatures) {
        int mid = -1;
        while (start <= end) {
            mid = (start + end) / 2;
            if (creatures[mid].getRank() < value.getRank())
                start = mid + 1;
            else if (creatures[mid].getRank() > value.getRank())
                end = mid - 1;
            else
                break;
        }
        if (creatures[mid].getRank() < value.getRank())
            return mid + 1;
        else if (creatures[mid].getRank() > value.getRank())
            return mid;

        return mid + 1;

    }

    @Override
    public void Sort(creature[] creatures, int start, int end) {
        for (int i = start + 1; i <= end; i++) {
            creature value = creatures[i];
            int insertLoc = binarySearch(start, i - 1, value, creatures) ;
            for (int j = i; j > insertLoc; j--) {
                creatures[j] = creatures[j - 1];
                System.out.println(creatures[j].getName() + ": " + (j - 1) + "->" + j);
            }
            creatures[insertLoc] = value;
            System.out.println(creatures[insertLoc].getName() + ": " + i + "->" + insertLoc);
        }
    }

}
*/
