package space;

import creature.Creature;

/**
 * 位置类，表示空间中的一个位置，每个位置可以与一个Creature相关联
 */
public class Position<T extends Creature> {
    private T holder;
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setHolder(T holder) {
        this.holder = holder;
    }

    public T getHolder() {
        return holder;
    }

//    public void unbindWith() {
//        if(holder != null) {
//            holder.setPosition(null);
//            holder = null;
//        }
//    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}