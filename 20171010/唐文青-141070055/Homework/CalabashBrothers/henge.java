package Homework.CalabashBrothers;

public class henge extends Pattern implements Formation {
    public henge() {
        this.row = 6;
        this.column = 2;
        this.type = Type.henge;
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
                if ((i + j) % 2 != 0) {
                    this.points[i][j].isempty = false;
                }
            }
        }
    }

    public void putmaincreature() {
        points[0][1].putcreature(new Scorpion(), Species.Snake);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (points[i][j].isempty == false && points[i][j].species == Species.Flower) {
                    points[i][j].putcreature(new Underling(), Species.Underling);
                }
            }
        }
    }

}
