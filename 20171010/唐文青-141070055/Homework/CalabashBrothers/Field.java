package Homework.CalabashBrothers;

import java.lang.Math;

public class Field extends Place implements Show {
    public int length;
    public int width;
    public Type badtype;
    Goodside goodside;
    Badside badside;

    public Field(int length, int width, Type badtype) {
        this.length = length;
        this.width = width;
        int len = (int) Math.ceil(length / 2);
        this.goodside = new Goodside(len, width);
        this.badside = new Badside(length - len, width, badtype);
    }

    public void setfield(int length, int width, Type badtype) {
        this.length = length;
        this.width = width;
        int len = (int) Math.ceil(length / 2);
        this.goodside = new Goodside(len, width);
        this.badside = new Badside(length - len, width, badtype);
        this.goodside.inipattern();
        this.goodside.getpattern();
        this.badside.inipattern(badtype);
        this.badside.getpattern();
    }

    public void showyourself() {
        this.goodside.showyourself();
        for (int i = 0; i < width; i++) {
            System.out.print("= ");
        }
        System.out.print("\n");
        this.badside.showyourself();
    }
}
