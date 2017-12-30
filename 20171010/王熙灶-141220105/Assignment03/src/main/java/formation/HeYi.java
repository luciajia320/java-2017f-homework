package formation;

import formation.exception.FormationException;
import formation.util.Point;
import formation.util.XmlReader;
import space.Space;

import java.util.ArrayList;

/**
 * 鹤翼阵法:
 *            X
 *         X
 *      X
 *   X
 *      X
 *         X
 *            X
 */
public final class HeYi extends BasicFormation {
    /**
     * @param space, 摆放阵法的空间
     * @param x, 阵头的x方向位置
     * @param y, 阵头的y方向位置
     * 这里的x方向表示从上到下，y方向表示从左到右
     */
    public HeYi(Space space, int x, int y) throws FormationException {
        super(space, x, y);
        ArrayList<Point> points = XmlReader.readFromFile("HeYi.xml");
        try {
            positions.set(0, space.getPos(current_x+points.get(0).x, current_y+points.get(0).y));
            positions.set(1, space.getPos(current_x+points.get(1).x, current_y+points.get(1).y));
            positions.set(2, space.getPos(current_x+points.get(2).x, current_y+points.get(2).y));
            positions.set(3, space.getPos(current_x+points.get(3).x, current_y+points.get(3).y));
            positions.set(4, space.getPos(current_x+points.get(4).x, current_y+points.get(4).y));
            positions.set(5, space.getPos(current_x+points.get(5).x, current_y+points.get(5).y));
            positions.set(6, space.getPos(current_x+points.get(6).x, current_y+points.get(6).y));
        } catch (IndexOutOfBoundsException e) {
            throw new FormationException("HeYi");
        }
    }
}