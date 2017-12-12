package Homework.CalabashBrothers;
import Homework.CalabashBrothers.*;

public class Place implements Show{
    protected int length;
    protected int width;
    Point[][] points;

    public void space(int length, int width) {
        this.length = length;
        this.width = width;
        this.points = new Point[length][width];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                points[i][j] = new Point(i, j);
            }
        }
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength(int length) {
        return length;
    }

    public int getWidth(int width) {
        return width;
    }


    public void showyourself(){
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                this.points[i][j].showyourself();
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }

}
