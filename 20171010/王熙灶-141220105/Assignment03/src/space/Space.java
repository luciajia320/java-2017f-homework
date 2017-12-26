package space;

import creature.Creature;

/**
 * 空间类，表示一个 N*N 的二维空间
 */
public class Space {
    private int size;
    private Position[][] Loc;

    public Space(int size) {
        this.size = size;
        Loc = new Position[size][];

        for(int i = 0; i < size; i++) {
            Loc[i] = new Position[size];
            for(int j = 0; j < size; j++) {
                Loc[i][j] = new Position();
            }
        }
    }

    /**
     * @param creature, 一个生物体
     * @param x, x坐标位置
     * @param y, y坐标位置
     * 表示在空间的(x, y)位置上放置生物体creature
     */
    public void positionSetter(Creature creature, int x, int y) {
        if(x > size || y > size) {
            return;
        }
        Loc[x][y].setHolder(creature);
        creature.setPosition(Loc[x][y]);
    }

    /**
     * @param x, x坐标位置
     * @param y, y坐标位置
     * @return 空间中(x, y)上的位置
     */
    public Position getPos(int x, int y) {
        if(x > size || y > size) {
            return null;
        }
        return Loc[x][y];
    }

    /**
     * @return 返回空间的大小N
     */
    public int getSize() {
        return size;
    }

    /**
     * @return 返回整个空间的状态布局
     * 如果某个位置上有生物体存在，则输出生物体对应的名字；
     * 否则输出 []
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(Loc[i][j].getHolder() == null) stringBuilder.append("[] ");
                else stringBuilder.append(Loc[i][j].getHolder().toString()).append(' ');
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }
}