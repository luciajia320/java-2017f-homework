package orders;

import java.util.ArrayList;
import java.util.List;

public class Heyi extends Order {
    public Heyi(Direction direction, Point controlPoint, int N, int num) {
        super(direction, controlPoint, N, num);
    }

    @Override
    public List<Point> order() {
        int k = (num - 1)/2;
        List<Point> ret = new ArrayList<>();
        ret.add(new Point(controlPoint.getX(),controlPoint.getY()));
        for (int i = 1 ; i <= k ;i++) {
            int centerX = controlPoint.getX() - i * direction.dx;
            int centerY = controlPoint.getY() - i * direction.dy;

            int point1X = centerX + direction.dy * i;
            int point1Y = centerY + direction.dx * i;

            int point2X = centerX - direction.dy * i;
            int point2Y = centerY - direction.dx * i;

            if (!inRange(point1X,point1Y) || !inRange(point2X,point2Y))
                return null;

            ret.add(new Point(point1X,point1Y));
            ret.add(new Point(point2X,point2Y));
        }
        return ret;
    }
}
