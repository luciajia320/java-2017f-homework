package Homework.CalabashBrothers;
import java.util.Random;

public class heyi extends Pattern implements Formation {
    public heyi() {
        this.row = 4;
        this.column = 7;
        this.type = Type.heyi;
        this.points = new Point[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                this.points[i][j] = new Point(i, j);
            }
        }
    }

    public void setformat() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (i + j == 3) {
                    this.points[i][j].isempty = false;
                    this.points[i][column - j - 1].isempty = false;
                }
            }
        }
    }

    public void putmaincreature() {
        points[0][3].putcreature(new Scorpion(),Species.Scorpion);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (points[i][j].isempty == false && points[i][j].species == Species.Flower ) {
                    points[i][j].putcreature(new Underling(), Species.Underling);
                }
            }
        }
    }

}
