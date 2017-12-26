package formation;

import space.Position;
import space.Space;

/**
 * 锋矢阵法：
 *      X
 *   X  X  X  X  X
 *      X
 */
public class FengShi extends BasicFormation {
    /**
     * @param space, 摆放阵法的空间
     * @param x, 阵头的x方向位置
     * @param y, 阵头的y方向位置
     * 这里的x方向表示从上到下，y方向表示从左到右
     */
    public FengShi(Space space, int x, int y) {
        super(space, x, y);
    }

    /**
     * @return 返回阵法中的下一个位置
     */
    public Position Next() {
        switch (no) {
            case 0: no++; return space.getPos(current_x, current_y);
            case 1: no++; return space.getPos(current_x-1, current_y+1);
            case 2: no++; return space.getPos(current_x, current_y+1);
            case 3: no++; return space.getPos(current_x+1, current_y+1);
            case 4: no++; return space.getPos(current_x, current_y+2);
            case 5: no++; return space.getPos(current_x, current_y+3);
            case 6: no++; return space.getPos(current_x, current_y+4);
            default: no = 0; return null;
        }
    }
}