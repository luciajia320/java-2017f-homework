package Homework.CalabashBrothers;
import java.lang.Math;

public class Badside extends Place{
    public Badside(int length, int width, Type type) {
        this.length = length;
        this.width = width;
        switch (type){
            case heyi:
                this.pattern = new heyi();
                this.type = Type.heyi;
                break;
            case henge:
                this.pattern = new henge();
                this.type = Type.henge;
                break;
        }
        this.points = new Point[length][width];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                this.points[i][j] = new Point(i, j);
            }
        }
    }

    public void inipattern(Type type) {
        pattern.setformat();
        pattern.putmaincreature();
        switch (type){
            case heyi:
                this.putextracreature(4, 7, Type.heyi);
                break;
            case henge:
                this.putextracreature(6, 2, Type.henge);
                break;
        }
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
