package Types;

/**
 * 二维向量，用于表示位置坐标信息
 */
public class Vector2 {
    private int x, y;

    public Vector2(){
        this.x = -1;
        this.y = -1;
    }

    public Vector2(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Vector2 add(Vector2 another){
        Vector2 result = new Vector2(this.x + another.getX(), this.y + another.getY());
        return result;
    }

    public boolean lessThan(Vector2 another){
        return (this.x < another.getX()) || ((this.x == another.getX()) && (this.y < another.getY()));
    }
}
