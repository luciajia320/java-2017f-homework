package space;

import creature.Creature;

import java.util.ArrayList;
import java.util.List;

/**
 * 空间类，表示一个 N*N 的二维空间
 */
public class Space {
    private int size;
    private List<List<Position<Creature>>> Loc;

    public Space(int size) {
        this.size = size;
        Loc = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            List<Position<Creature>> ls = new ArrayList<>();
            for(int j = 0; j < size; j++) {
                ls.add(new Position<>());
            }
            Loc.add(ls);
        }
    }

    /**
     * @param creature, 一个生物体
     * @param x, x坐标位置
     * @param y, y坐标位置
     * 表示在空间的(x, y)位置上放置生物体creature
     */
    public void creature_position_setter(Creature creature, int x, int y) {
        if(x > size || y > size) {
            return;
        }
        Position<Creature> pos = Loc.get(x).get(y);
        pos.setHolder(creature);
        creature.setPosition(pos);
    }

    /**
     * @param x, x坐标位置
     * @param y, y坐标位置
     * @return 空间中(x, y)上的位置
     */
    public Position<Creature> getPos(int x, int y) {
        if(x > size || y > size) {
            return null;
        }
        return Loc.get(x).get(y);
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
        for(List<Position<Creature>> positions: Loc) {
            for(Position<Creature> position: positions) {
                if(position.getHolder() == null) {
                    stringBuilder.append("[] ");
                }
                else {
                    stringBuilder.append(position.getHolder().toString()).append(' ');
                }
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }
}