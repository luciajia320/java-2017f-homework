
/**
 * Created by Administrator on 2018/1/3.
 */
public abstract class Creature {
    private int x,y,name;

    public abstract void moveTo(int endX, int endY) ;

    public abstract void swap(Creature creature);

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

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name+"("+x+","+y+")";
    }
}
