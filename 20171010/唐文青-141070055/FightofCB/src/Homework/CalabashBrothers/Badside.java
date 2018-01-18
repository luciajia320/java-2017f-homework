package Homework.CalabashBrothers;

import Homework.CalabashBrothers.Creatures.*;
import Homework.CalabashBrothers.Patterns.*;

import java.lang.Math;
import java.util.Random;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Badside extends Place {
    Type type;
    Pattern pattern;

    public Badside(int length, int width) {
        this.length = length;
        this.width = width;
        Random r = new Random();
        int t = r.nextInt(7);
        this.type = Type.values()[t + 1];
        switch (type) {
            case HEYI:
                this.pattern = new HeYi();
                break;
            case YANXING:
                this.pattern = new YanXing();
                break;
            case HENGE:
                this.pattern = new HengE();
                break;
            case YULIN:
                this.pattern = new YuLin();
                break;
            case FANGYUAN:
                this.pattern = new FangYuan();
                break;
            case YANYUE:
                this.pattern = new YanYue();
                break;
            case FENGSHI:
                this.pattern = new FengShi();
                break;
        }
        this.points = new Point[length][width];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                this.points[i][j] = new Point(i, j);
            }
        }
    }

    public void inipattern() {
        //If this isn't the first confrontation, clear the bad side and choose pattern again.
        if (this.pattern != null) {
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < width; j++) {
                    this.points[i][j].creature = new Flower();
                    this.points[i][j].species = Species.FLOWER;
                }
            }
            Random r = new Random();
            int t = r.nextInt(7);
            this.type = Type.values()[t + 1];
            switch (type) {
                case HEYI:
                    this.pattern = new HeYi();
                    break;
                case YANXING:
                    this.pattern = new YanXing();
                    break;
                case HENGE:
                    this.pattern = new HengE();
                    break;
                case YULIN:
                    this.pattern = new YuLin();
                    break;
                case FANGYUAN:
                    this.pattern = new FangYuan();
                    break;
                case YANYUE:
                    this.pattern = new YanYue();
                    break;
                case FENGSHI:
                    this.pattern = new FengShi();
                    break;
            }
        }
        SimpleDateFormat fm = new SimpleDateFormat("| yyyy/MM/dd HH:mm:ss:SSS |   ");
        System.out.println(fm.format(new Date(System.currentTimeMillis())) + "The Scorpion group choose " + this.type + " as their pattern.");
        pattern.setpattern();
        pattern.putmaincreature();
    }

    public void getpattern() throws PlaceExceedException{
        if(this.pattern.row > this.length || this.pattern.column > this.width){
            throw new PlaceExceedException("The pattern is too big for the bad side to contain!");
        }
        //Let Scorpion and Underlings in pattern move to the lawn.
        int inix = (int) Math.ceil((length - pattern.row) / 2);
        int iniy = (int) Math.ceil((width - pattern.column) / 2);
        for (int i = inix; i < inix + pattern.row; i++) {
            for (int j = iniy; j < iniy + pattern.column; j++) {
                this.points[i][j] = pattern.points[i - inix][j - iniy];
            }
        }
        //Let Snake get on the lawn.
        this.putsnake(this.length, this.width);
        SimpleDateFormat fm = new SimpleDateFormat("| yyyy/MM/dd HH:mm:ss:SSS |   ");
        System.out.println(fm.format(new Date(System.currentTimeMillis())) + "The bad side is ready!");
    }

    public void putsnake(int row, int column) {
        //Snake chooses a random position to stand on.
        //If Snake has stood on the lawn and a new confrontation begins, Grandpa need to choose a new position.
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (this.points[i][j].species == Species.SNAKE) {
                    this.points[i][j].putcreature(new Flower(), Species.FLOWER);
                }
            }
        }
        Random r1 = new Random();
        Random r2 = new Random();
        boolean flag = false;
        while (flag == false) {
            int t1 = r1.nextInt(row);
            int t2 = r2.nextInt(column);
            if (this.points[t1][t2].isempty == true) {
                flag = true;
                this.points[t1][t2].putcreature(new Snake(), Species.SNAKE);
            }
        }
        SimpleDateFormat fm = new SimpleDateFormat("| yyyy/MM/dd HH:mm:ss:SSS |   ");
        System.out.println(fm.format(new Date(System.currentTimeMillis())) + "Snake has chosen her position.");
    }


}
