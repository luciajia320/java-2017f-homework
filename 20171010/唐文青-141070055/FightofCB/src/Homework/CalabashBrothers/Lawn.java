package Homework.CalabashBrothers;

import java.lang.Math;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Lawn extends Place implements Show {
    public int length;
    public int width;
    Goodside goodside;
    Badside badside;

    public Lawn(int length, int width) {
        this.length = length;
        this.width = width;
    }

    public void setfield(int length, int width) {
        this.length = length;
        this.width = width;
        int len = (int) Math.ceil(length / 2);
        //The pattern of the good side won't need to change if the Calabash Brothers have stood on the lawn.
        if (this.goodside == null) {
            this.goodside = new Goodside(len, width);
        }
        if (this.badside == null) {
            this.badside = new Badside(length - len, width);
        }
        try {
            this.goodside.inipattern();
            this.goodside.getpattern();
            this.badside.inipattern();
            this.badside.getpattern();
        } catch (PlaceExceedException e) {
            e.printStackTrace();
        }
        SimpleDateFormat fm = new SimpleDateFormat("| yyyy/MM/dd HH:mm:ss:SSS |   ");
        System.out.println(fm.format(new Date(System.currentTimeMillis())) + "The confrontation now begins!");
    }

    public void showyourself() {
        this.goodside.showyourself();
        for (int i = 0; i < width; i++) {
            System.out.print("== ");
        }
        System.out.print("\n");
        this.badside.showyourself();
    }
}
