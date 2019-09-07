package space;

import creature.Creature;
import creature.plant.RandomPlant;

import java.util.ArrayList;
import java.util.List;

/**
 * 空间类，表示一个 N*N 的二维空间
 */
public class Space {
    private int size;
    private List<List<Position<Creature>>> positionss;

    public Space(int size) {
        this.size = size;
        positionss = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            List<Position<Creature>> ls = new ArrayList<>();
            for(int j = 0; j < size; j++) {
                Position<Creature> pos = new Position<>();
                pos.setHolder(RandomPlant.get());
                ls.add(pos);
            }
            positionss.add(ls);
        }
    }

    /**
     * @param creature, 一个生物体
     * @param x, x坐标位置
     * @param y, y坐标位置
     * 表示在空间的 (x, y) 位置上放置生物体 creature
     */
    public void creature_position_setter(Creature creature, int x, int y) {
        if(x > size || y > size) {
            throw new IndexOutOfBoundsException("method 'void creature_position_setter(Creature creature, int x, int y)' index out of bounds in class Space.");
        }
        Position<Creature> pos = positionss.get(x).get(y);
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
            throw new IndexOutOfBoundsException("method 'Position<Creature> getPos(int x, int y)' index out of bounds in class Space.");
        }
        return positionss.get(x).get(y);
    }

    /**
     * @return 返回空间的大小N
     */
    public int getSize() {
        return size;
    }

    /**
     * @return 返回整个空间的状态布局
     * 如果某个位置上有动物存在，则输出动物对于的图标；
     * 否则输出该位置上的默认植物图标
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(List<Position<Creature>> positions: positionss) {
            for(Position<Creature> position: positions) {
                stringBuilder.append(position.getHolder().toString()).append(' ');
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }
}