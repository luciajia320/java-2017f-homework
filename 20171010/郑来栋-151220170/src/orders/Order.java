package orders;

import Scene.Arena;
import com.sun.org.apache.xpath.internal.operations.Or;
import roles.Creature;

import java.util.List;


public abstract class Order {

    protected Direction direction;
    protected Point controlPoint;
    protected int N;
    protected int num;

    public Order(Direction direction,Point controlPoint,int N,int num)
    {
        this.direction = direction;
        this.controlPoint = controlPoint;
        this.N = N;
        this.num = num;
    }

    boolean inRange(int x,int y)
    {
        if (x >= N || x < 0)
            return false;
        if (y >= N || y < 0)
            return false;
        return true;
    }

    /*根据参数返回阵法的模板，实际上就是一些点*/
    abstract List<Point> order();

}
