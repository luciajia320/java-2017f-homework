package Homework.CalabashBrothers.Patterns;
import Homework.CalabashBrothers.*;
import Homework.CalabashBrothers.Creatures.*;

import java.text.SimpleDateFormat;
import java.util.*;

public final class ChangShe extends Pattern {
    public ChangShe() {
        this.row = 7;
        this.column = 1;
        this.type = Type.CHANGSHE;
        this.points = new Point[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                this.points[i][j] = new Point(i, j);
            }
        }
    }

    public void setpattern() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                this.points[i][j].isempty = false;
            }
        }
    }

    public void putmaincreature() {
        //First, call the Calabash Brothers Family to come.
        CalabrosFamily cbf = new CalabrosFamily();
        //Show the initial unorganized queue of Calabash Brothers
        SimpleDateFormat fm = new SimpleDateFormat("| yyyy/MM/dd HH:mm:ss:SSS |   ");
        System.out.print(fm.format(new Date(System.currentTimeMillis())) + "Calabash Brothers have got together:");
        cbf.showyourself();
        //Calabash Brothers line up in order, show the line.
        cbf.sorting();
        System.out.print(fm.format(new Date(System.currentTimeMillis())) + "Calabash Brothers have lined up in order:");
        cbf.showyourself();
        //Put Calabash Brothers into the ChangShe pattern according to their line.
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (this.points[i][j].isempty == false && this.points[i][j].species == Species.FLOWER) {
                    //Let the eldest one take the lead.
                    this.points[i][j].putcreature(cbf.CBfamily.get(row - i - 1), Species.CALABASHBRO);
                }
            }
        }

    }

}
