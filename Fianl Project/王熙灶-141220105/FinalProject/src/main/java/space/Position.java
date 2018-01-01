package space;

import creature.Creature;

/**
 * 位置类，表示空间中的一个点，每个点上可以放置一个Creature
 */
public class Position<T extends Creature> {
    private T holder;
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
        holder = null;
    }

    public void setHolder(T holder) {
        this.holder = holder;
    }

    public T getHolder() {
        return holder;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}