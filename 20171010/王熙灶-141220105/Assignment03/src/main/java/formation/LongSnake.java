package formation;

import space.Space;

/**
 * 长蛇阵法:
 *   X X X X X X X
 */
public final class LongSnake extends BasicFormation {
    /**
     * @param space, 摆放阵法的空间
     * @param x, 阵头的x方向位置
     * @param y, 阵头的y方向位置
     * 这里的x方向表示从上到下，y方向表示从左到右
     */
    public LongSnake(Space space, int x, int y) {
        super(space, x, y);
        positions.set(0, space.getPos(current_x, current_y));
        positions.set(1, space.getPos(current_x+1, current_y));
        positions.set(2, space.getPos(current_x+2, current_y));
        positions.set(3, space.getPos(current_x+3, current_y));
        positions.set(4, space.getPos(current_x+4, current_y));
        positions.set(5, space.getPos(current_x+5, current_y));
        positions.set(6, space.getPos(current_x+6, current_y));
    }
}