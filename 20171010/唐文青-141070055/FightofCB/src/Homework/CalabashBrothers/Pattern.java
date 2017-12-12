package Homework.CalabashBrothers;

public abstract class Pattern implements Show{
    public int row;
    public int column;
    public Type type;
    public Point[][] points;

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

    public abstract void setpattern();

    public abstract void putmaincreature();


    public void showyourself(){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                points[i][j].showyourself();
            }
        }
    }

}
