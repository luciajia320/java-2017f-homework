package formation;

import exception.FormationException;
import util.Dom4jXmlReader;
import util.Point;
import space.Space;

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
     * @param space, 摆放阵法的空间
     * @param x, 阵头的x方向位置
     * @param y, 阵头的y方向位置
     * 这里的x方向表示从上到下，y方向表示从左到右
     */
    public FangYuan(Space space, int x, int y) throws FormationException {
        super(space, x, y);
        ArrayList<Point> points = Dom4jXmlReader.read("FangYuan.xml");
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