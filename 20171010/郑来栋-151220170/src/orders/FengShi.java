package orders;

import java.util.ArrayList;
import java.util.List;

public class FengShi extends Order {
    public FengShi(Direction direction, Point controlPoint, int N, int num) {
        super(direction, controlPoint, N, num);
    }

    @Override public List<Point> order() {
        List<Point> ret = new ArrayList<>();
        ret.add(new Point(controlPoint.getX(),controlPoint.getY()));
        for (int i = 1 ; i <= 2 ;i++) {
            int centerX = controlPoint.getX() - i * direction.dx;
            int centerY = controlPoint.getY() - i * direction.dy;

            int point1X = centerX + direction.dy * i;
            int point1Y = centerY + direction.dx * i;

            int point2X = centerX - direction.dy * i;
            int point2Y = centerY - direction.dx * i;

            if (!inRange(point1X,point1Y) || !inRange(point2X,point2Y))
                return null;

            ret.add(new Point(centerX,centerY));
            ret.add(new Point(point1X,point1Y));
            ret.add(new Point(point2X,point2Y));
        }

        int remainder = num - 7;
        for (int i = 3 ; i < 3 + remainder ; i++)
        {
            int centerX = controlPoint.getX() - i * direction.dx;
            int centerY = controlPoint.getY() - i * direction.dy;
            ret.add(new Point(centerX,centerY));
        }
        return ret;
    }
}
