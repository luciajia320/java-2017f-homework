
public class CalabashBoy {

    public enum Attribute {
        RANK, COLOR
    }

    private static final String[] NAMES = {
            "大娃", "二娃", "三娃", "四娃", "五娃", "六娃", "七娃"
    };
    private static final String[] COLORS = {
            "红色", "橙色", "黄色", "绿色", "青色", "蓝色", "紫色"
    };

    private int rank, position;
    CalabashBoy(int rank, int position) {
        this.rank = rank;
        this.position = position;
    }

    public void report(Attribute attr) {
        switch (attr) {
            case RANK:
                System.out.println(NAMES[rank]);
                break;
            case COLOR:
                System.out.println(COLORS[rank]);
                break;
        }
    }
    public int compareTo(CalabashBoy cb, Attribute attr) {
        return rank - cb.rank;
    }
    public void moveTo(int toPos) {
        if (toPos == position) {
            return;
        }
        reportSwap(position, toPos);
        position = toPos;
    }
    private void reportSwap(int from, int to) {
        System.out.println(NAMES[rank] + ":" + from + " -> " + to);
    }
}
