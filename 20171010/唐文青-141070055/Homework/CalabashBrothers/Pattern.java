package Homework.CalabashBrothers;
import java.util.Random;

public class Pattern implements Show, Formation{
    public int row;
    public int column;
    public Type type;
    Point[][] points;

    public Pattern(){
        this.row = row;
        this.column = column;
        this.type = type;
        this.points = new Point[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                this.points[i][j] = new Point(i, j);
            }
        }
    }

    public void setformat(){
        System.out.print("!");
    }

    public void putmaincreature() {
        System.out.print("!");
    }


    public void showyourself(){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                points[i][j].showyourself();
            }
        }
    }

}
