package creature;

import space.Position;

/**
 * 基类，生物体类
 */
public class Creature {
    private Position<Creature> position;

    public Position<Creature> getPosition() {
        return position;
    }

    public void setPosition(Position<Creature> position) {
        this.position = position;
    }
}