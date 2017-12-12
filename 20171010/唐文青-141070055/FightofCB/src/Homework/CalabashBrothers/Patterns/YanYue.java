package Homework.CalabashBrothers.Patterns;

import Homework.CalabashBrothers.*;
import Homework.CalabashBrothers.Creatures.*;

public final class YanYue extends Pattern {
    public YanYue() {
        this.row = 6;
        this.column = 9;
        this.type = Type.YANYUE;
        this.points = new Point[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                this.points[i][j] = new Point(i, j);
            }
        }
    }

    @Override
    public void setpattern() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (i < 4 && (i + j == 4 || j - i == 4)) {
                    this.points[i][j].isempty = false;
                }
                if (i > 0 && (i + j == 5 || j - i == 3)) {
                    this.points[i][j].isempty = false;
                }
            }
        }
        this.points[0][3].isempty = false;
        this.points[0][5].isempty = false;
        this.points[2][4].isempty = false;
    }

    @Override
    public void putmaincreature() {
        //First, let the Scorpion take the lead.
        points[0][4].putcreature(new Scorpion(), Species.SCORPION);
        //Then, the underlings gather together to support the Scorpion.
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (points[i][j].isempty == false && points[i][j].species == Species.FLOWER) {
                    points[i][j].putcreature(new Underling(), Species.UNDERLING);
                }
            }
        }
    }
}
