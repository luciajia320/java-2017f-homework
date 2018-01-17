package formation;

import exception.FormationException;
import util.FormationReader;
import util.Point;
import static util.Constant.space;

import java.util.ArrayList;

/**
 * 方圆阵法：
 *         X
 *      X     X
 *   X
 *      X     X
 *         X
 */
public final class FangYuan extends BasicFormation {
    /**
     * @param x, 阵头的x方向位置
     * @param y, 阵头的y方向位置
     * 这里的x方向表示从上到下，y方向表示从左到右
     */
    public FangYuan(int x, int y) throws FormationException {
        super(x, y);
        ArrayList<Point> points = FormationReader.read("FangYuan.xml");
        try {
            positions.set(0, space.getPos(current_y+points.get(0).y, current_x+points.get(0).x));
            positions.set(1, space.getPos(current_y+points.get(1).y, current_x+points.get(1).x));
            positions.set(2, space.getPos(current_y+points.get(2).y, current_x+points.get(2).x));
            positions.set(3, space.getPos(current_y+points.get(3).y, current_x+points.get(3).x));
            positions.set(4, space.getPos(current_y+points.get(4).y, current_x+points.get(4).x));
            positions.set(5, space.getPos(current_y+points.get(5).y, current_x+points.get(5).x));
            positions.set(6, space.getPos(current_y+points.get(6).y, current_x+points.get(6).x));
        } catch (IndexOutOfBoundsException e) {
            throw new FormationException("FangYuan");
        }
    }
}