package space;

import creature.Creature;

/**
 * 位置类，表示空间中的一个点，每个点上可以放置一个Creature
 */
public class Position<T extends Creature> {
    private T holder;

    public Position() {
        holder = null;
    }

    public void setHolder(T holder) {
        this.holder = holder;
    }

    public void clearHolder() {
        setHolder(null);
    }

    public Creature getHolder() {
        return holder;
    }
}