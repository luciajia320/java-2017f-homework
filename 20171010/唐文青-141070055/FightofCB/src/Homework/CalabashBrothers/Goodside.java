package Homework.CalabashBrothers;

import Homework.CalabashBrothers.Creatures.Flower;
import Homework.CalabashBrothers.Creatures.Grandpa;
import Homework.CalabashBrothers.Patterns.ChangShe;

import java.lang.Math;
import java.util.Date;
import java.util.Random;
import java.text.SimpleDateFormat;

//The place where Calabash Brothers stand in.
public class Goodside extends Place {
    Type type;
    Pattern pattern;

    public Goodside(int length, int width) {
        this.length = length;
        this.width = width;
        this.points = new Point[length][width];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                this.points[i][j] = new Point(i, j);
            }
        }
    }

    public void inipattern() {
        if (this.pattern != null) {
            //Only Grandpa need to choose a new position.The Calabash Brothers don't have to move.
            SimpleDateFormat fm = new SimpleDateFormat("| yyyy/MM/dd HH:mm:ss:SSS |   ");
            System.out.println(fm.format(new Date(System.currentTimeMillis())) + "The pattern of the good side remains the same.");
            for (int i = 0; i < this.length; i++) {
                for (int j = 0; j < this.width; j++) {
                    if(this.points[i][j].species == Species.GRANDPA){
                        this.points[i][j].putcreature(new Flower(), Species.FLOWER);
                    }
                }
            }
        }
        else {
            this.pattern = new ChangShe();
            this.type = Type.CHANGSHE;
            //Make the Calabash Brothers get in the Pattern
            this.pattern.setpattern();
            this.pattern.putmaincreature();
        }
    }

    public void getpattern() throws PlaceExceedException{
        if(this.pattern.row > this.length || this.pattern.column > this.width){
            throw new PlaceExceedException("The pattern is too big for the good side to contain!");
        }
        //Let Calabash Brothers in pattern move to the lawn.
        int inix = (int) Math.ceil((length - pattern.row) / 2);
        int iniy = (int) Math.ceil((width - pattern.column) / 2);
        for (int i = inix; i < inix + pattern.row; i++) {
            for (int j = iniy; j < iniy + pattern.column; j++) {
                this.points[i][j] = pattern.points[i - inix][j - iniy];
            }
        }
        //Let Grandpa get on the lawn.
        this.putgrandpa(this.length, this.width);
        SimpleDateFormat fm = new SimpleDateFormat("| yyyy/MM/dd HH:mm:ss:SSS |   ");
        System.out.println(fm.format(new Date(System.currentTimeMillis())) + "The good side is ready!");
    }

    public void putgrandpa(int row, int column) {
        //Grandpa chooses a random position to stand on.
        Random r1 = new Random();
        Random r2 = new Random();
        boolean flag = false;
        while (flag == false) {
            int t1 = r1.nextInt(row);
            int t2 = r2.nextInt(column);
            if (this.points[t1][t2].isempty == true) {
                flag = true;
                this.points[t1][t2].putcreature(new Grandpa(), Species.GRANDPA);
            }
        }
        SimpleDateFormat fm = new SimpleDateFormat("| yyyy/MM/dd HH:mm:ss:SSS |   ");
        System.out.println(fm.format(new Date(System.currentTimeMillis())) + "Grandpa has chosen his position.");
    }

}
