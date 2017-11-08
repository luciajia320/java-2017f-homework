package Homework.CalabashBrothers;

import com.sun.org.apache.bcel.internal.generic.SWITCH;

public class changshe extends Pattern implements Formation {
    public changshe() {
        this.row = 7;
        this.column = 1;
        this.type = Type.changshe;
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
                this.points[i][j].isempty = false;
            }
        }
    }

    public void putmaincreature() {
        for (int j = 0; j < column; j++) {
            for (int i = 0; i < row; i++) {
                if (points[i][j].isempty == false && points[i][j].species == Species.Flower) {
                    switch (i) {
                        case 6:
                            points[i][j].putcreature(new CalabashBros(Rank.First, Color.Red), Species.Calabashbro);
                            break;
                        case 5:
                            points[i][j].putcreature(new CalabashBros(Rank.Second, Color.Orange), Species.Calabashbro);
                            break;
                        case 4:
                            points[i][j].putcreature(new CalabashBros(Rank.Third, Color.Yellow), Species.Calabashbro);
                            break;
                        case 3:
                            points[i][j].putcreature(new CalabashBros(Rank.Fourth, Color.Green), Species.Calabashbro);
                            break;
                        case 2:
                            points[i][j].putcreature(new CalabashBros(Rank.Fifth, Color.Cyan), Species.Calabashbro);
                            break;
                        case 1:
                            points[i][j].putcreature(new CalabashBros(Rank.Sixth, Color.Blue), Species.Calabashbro);
                            break;
                        case 0:
                            points[i][j].putcreature(new CalabashBros(Rank.Seventh, Color.Purple), Species.Calabashbro);
                            break;
                    }
                }
            }
        }
    }

}
