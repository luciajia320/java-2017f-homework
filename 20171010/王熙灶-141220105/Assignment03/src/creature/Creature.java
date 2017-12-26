package creature;

import space.Position;

/**
 * 基类，生物体类
 */
public class Creature {
    private Position position;

    public Creature() {
        position = null;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}