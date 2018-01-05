package creature;

import space.Position;


import java.awt.*;

/**
 * 基类，生物体类
 */
public abstract class Creature {
    private Position<Creature> position;

    public Position<Creature> getPosition() {
        return position;
    }

    public void setPosition(Position<Creature> position) {
        this.position = position;
    }

    public void unbindWith() {
        if(position != null) {
            position.unbindWith();
        }
    }

    abstract public Image getImage();

    /**
     * @param c, 另一个生物体
     * @return 当前生物体与生物体c的曼哈顿距离
     */
    public int distance(Creature c) {
        int dis_x = Math.abs(position.getX() - c.getPosition().getX());
        int dis_y = Math.abs(position.getY() - c.getPosition().getY());
        return dis_x + dis_y;
    }
}