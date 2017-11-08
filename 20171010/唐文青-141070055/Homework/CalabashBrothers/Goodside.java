package Homework.CalabashBrothers;

import java.lang.Math;

public class Goodside extends Place {
    public Goodside(int length, int width) {
        this.length = length;
        this.width = width;
        this.pattern = new changshe();
        this.type = Type.changshe;
        this.points = new Point[length][width];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                this.points[i][j] = new Point(i, j);
            }
        }
    }

    public void inipattern() {
        this.pattern.setformat();
        this.pattern.putmaincreature();
        this.putextracreature(7, 1, Type.changshe);
    }

    public void getpattern() {
        int inix = (int) Math.ceil((length - pattern.row) / 2);
        int iniy = (int) Math.ceil((width - pattern.column) / 2);
        for (int i = inix; i < inix + pattern.row; i++) {
            for (int j = iniy; j < iniy + pattern.column; j++) {
                this.points[i][j] = pattern.points[i - inix][j - iniy];
            }
        }
    }

}
