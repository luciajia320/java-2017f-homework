package orders;

import Scene.Arena;
import Scene.Position;
import roles.Creature;

import java.util.*;

public class LongSnake extends Order {

    public LongSnake(Direction direction, Point controlPoint, int N,int num) {
        super(direction, controlPoint, N, num);
    }

    @Override public List<Point> order() {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < num ; i++)
        {
            int x = controlPoint.getX() + i * direction.dx;
            int y = controlPoint.getY() + i * direction.dy;
            if (!inRange(x,y))
                return null;
            Point point = new Point(x,y);
            points.add(point);
        }
        return points;
    }
}
