package entity;

/* 位置坐标, 以左上角为原点, 横向x轴正方向, 纵向y轴正方向展开 */

public class Location {
    public Location() { x = y = 0; }

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setLocation(int x, int y) { setX(x); setY(y); }

    private int x, y;
}
