package formation;

import creature.Creature;
import space.Position;
import space.Space;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * 默认阵法中只允许有七个位置
 */
public class BasicFormation implements Iterable<Position<Creature>> {
    protected int current_x;
    protected int current_y;
    protected Space space;
    // 每个阵法只由七个位置构成
    protected ArrayList<Position<Creature>> positions = new ArrayList<>(Collections.nCopies(7, null));

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
    }

    /**
     * 取消当前阵法中每个位置上的生物与阵法位置的关联
     */
    public void clear() {
        for (Position<Creature> pos : this) {
            pos.getHolder().setPosition(null);
            pos.clearHolder();
        }
    }

    @Override
    public Iterator<Position<Creature>> iterator() {
        return new FormationIterator();
    }

    private class FormationIterator implements Iterator<Position<Creature>> {
        int no = 0;

        @Override
        public boolean hasNext() {
            return no < 7;
        }

        @Override
        public Position<Creature> next() {
            return positions.get(no++);
        }
    }
}