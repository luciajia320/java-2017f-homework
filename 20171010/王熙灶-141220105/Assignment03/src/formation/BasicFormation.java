package formation;

import space.Position;
import space.Space;

/**
 * 默认阵法中只允许有七个位置
 */
public class BasicFormation {
    protected int current_x;
    protected int current_y;
    protected Space space;
    protected Position current_pos;
    protected int no;

    /**
     * @param space, 摆放阵法的空间
     * @param x, 阵头的x方向位置
     * @param y, 阵头的y方向位置
     * 这里的x方向表示从上到下，y方向表示从左到右
     */
    public BasicFormation(Space space, int x, int y) {
        this.current_x = x;
        this.current_y = y;
        this.space = space;
        current_pos = space.getPos(x, y);
        no = 0;
    }

    /**
     * 恢复默认序数，重新开始获取位置
     */
    public void reset() {
        no = 0;
    }

    /**
     * @return 返回阵法中的下一个位置
     */
    public Position Next() {
        return null;
    }
}