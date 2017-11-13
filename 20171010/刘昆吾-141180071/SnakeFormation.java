

public class SnakeFormation extends Formation {
    public SnakeFormation(String matter) {
        super(7, 2);
        for (int i = 0; i < 7; i++) {
            this.content[i][0] = matter;
        }
        this.content[0][0] = "一";
        this.content[1][0] = "二";
        this.content[2][0] = "三";
        this.content[3][0] = "四";
        this.content[4][0] = "五";
        this.content[5][0] = "六";
        this.content[6][0] = "七";
        this.content[3][1] = "爷";
    }
}
