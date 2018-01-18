public class BubbleSorter {
    public void sort(Huluwa[] huluwa) {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7-1-i; j++) {
                if ( ((Comparable)(huluwa[j])).biggerThan((Huluwa)(huluwa[j+1])) ) {
                    Huluwa huluwaTmp = huluwa[j];
                    huluwa[j] = huluwa[j+1];
                    huluwa[j+1] = huluwaTmp;
                    Battlefield.setPosition(huluwa[j].row, huluwa[j].col, huluwa[j].character);
                    Battlefield.setPosition(huluwa[j+1].row, huluwa[j+1].col, huluwa[j+1].character);
                }
            }
        }
    }

}
