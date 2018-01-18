package Homework.CalabashBrothers.Patterns;

import Homework.CalabashBrothers.*;
import Homework.CalabashBrothers.Creatures.*;

public final class FangYuan extends Pattern {
    public FangYuan() {
        this.row = 5;
        this.column = 5;
        this.type = Type.FANGYUAN;
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
                if (i <= 2 && (i + j == 2 || j - i == 2)) {
                    this.points[i][j].isempty = false;
                }
                else if(i > 2 && (i - j == 2 || i + j == 6)){
                    this.points[i][j].isempty = false;
                }
            }
        }
    }

    @Override
    public void putmaincreature() {
        //First, let the Scorpion take the lead.
        points[0][2].putcreature(new Scorpion(), Species.SCORPION);
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
