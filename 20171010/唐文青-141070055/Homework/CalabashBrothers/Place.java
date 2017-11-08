package Homework.CalabashBrothers;

import java.util.Random;

public class Place implements Show{
    public int length;
    public int width;
    Type type;
    Pattern pattern;
    Point[][] points;

    public void space(int length, int width, Type type, Pattern pattern) {
        this.length = length;
        this.width = width;
        this.type = type;
        this.pattern = pattern;
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

    public void putextracreature(int row, int column, Type type){
        Random r1 = new Random();
        Random r2 = new Random();
        boolean flag = false;
        while (flag == false){
            int t1 = r1.nextInt(row);
            int t2 = r2.nextInt(column);
            if(this.points[t1][t2].isempty == true){
                flag = true;
                if(type == Type.changshe) {
                    this.points[t1][t2].putcreature(new Grandpa(),Species.Grandpa);
                }
                else {
                    this.points[t1][t2].putcreature(new Snake(),Species.Snake);
                }
            }
        }
    }
    public void showyourself(){
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                this.points[i][j].showyourself();
            }
            System.out.print("\n");
        }
    }

}
