import java.util.Random;

public class SevenBrothers {

    public enum SortType {
        BUBBLE, BIN_INSERT
    }

    private final int NUM_BROS = 7;
    private CalabashBoy[] calaBoys = new CalabashBoy[NUM_BROS];
    SevenBrothers() {
        for (int i = 0; i < NUM_BROS; i ++) {
            calaBoys[i] = new CalabashBoy(i, i);
        }
    }

    public void lineUpRandomly() {
        final int NUM_RANDOM_SWAP = 7;
        Random rand = new Random();
        for (int i = 0; i < NUM_RANDOM_SWAP; i ++) {
            int a = rand.nextInt(NUM_BROS);
            int b = rand.nextInt(NUM_BROS);
            swap(a, b);
        }
        System.out.println("---line up randomly---");
    }

    public void report(CalabashBoy.Attribute attr) {
        for (int i = 0; i < NUM_BROS; i ++) {
            calaBoys[i].report(attr);
        }
    }

    public void sort(SortType st, CalabashBoy.Attribute key) {
        System.out.println("---go sort : " + st.name() + " ---");
        switch (st) {
            case BUBBLE:
                bubbleSort(key);
                break;
            case BIN_INSERT:
                binInsertSort(key);
                break;
        }
        System.out.println("---sorted : " + st.name() + " ---");
    }

    private void swap(int a, int b) {
        if (a == b) {
            return;
        }
        CalabashBoy temp = calaBoys[a];
        calaBoys[a] = calaBoys[b];
        calaBoys[b] = temp;
    }
    private void bubbleSort(CalabashBoy.Attribute key) {
        for (int i = NUM_BROS - 1; i >= 1; i --) {
            for (int k = 0; k < i; k ++) {
                if (calaBoys[k].compareTo(calaBoys[k + 1], key) > 0) {
                    swap(k, k + 1);
                    calaBoys[k].moveTo(k);
                    calaBoys[k + 1].moveTo(k + 1);
                }
            }
        }
    }
    private void binInsertSort(CalabashBoy.Attribute key) {
        for (int i = 1; i < NUM_BROS; i ++) {
            binInsert(0, i, key);
        }
    }
    private void binInsert(int begin, int end, CalabashBoy.Attribute key) {
        if (end <= begin) {
            return;
        }
        int pos = binSearch(begin, end, calaBoys[end], key);
        if (pos == end) {
            return;
        }
        CalabashBoy temp = calaBoys[end];
        for (int i = end - 1; i >= pos; i --) {
            calaBoys[i + 1] = calaBoys[i];
            calaBoys[i + 1].moveTo(i + 1);
        }
        calaBoys[pos] = temp;
        calaBoys[pos].moveTo(pos);
    }
    private int binSearch(int begin, int end,
                          CalabashBoy cb,
                          CalabashBoy.Attribute key) {
        if (end <= begin) {
            return end;
        }
        int mid = (begin + end - 1) / 2;
        if (cb.compareTo(calaBoys[mid], key) > 0) {
            return binSearch(mid + 1, end, cb, key);
        }
        else {
            return binSearch(begin, mid, cb, key);
        }
    }
}
