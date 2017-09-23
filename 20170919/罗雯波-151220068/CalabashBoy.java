
public class CalabashBoy {

    public enum Attribute {
        NAME, COLOR
    }

    private static final String[] NAMES = {
            "大娃", "二娃", "三娃", "四娃", "五娃", "六娃", "七娃"
    };
    private static final String[] COLORS = {
            "红色", "橙色", "黄色", "绿色", "青色", "蓝色", "紫色"
    };

    private int number;
    CalabashBoy(int number) {
        this.number = number;
    }

    public void report(Attribute attr) {
        switch (attr) {
            case NAME:
                System.out.println(NAMES[number]);
                break;
            case COLOR:
                System.out.println(COLORS[number]);
                break;
        }
    }
    public int compareTo(CalabashBoy cb, Attribute attr) {
        return number - cb.number;
    }
    public void reportSwap(int from, int to) {
        System.out.println(NAMES[number] + ":" + from + " -> " + to);
    }
}
