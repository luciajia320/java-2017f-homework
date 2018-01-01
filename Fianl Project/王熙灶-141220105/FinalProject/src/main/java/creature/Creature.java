package creature;

import space.Position;

import java.awt.*;

/**
 * 基类，生物体类
 */
public class Creature {
    private Position<Creature> position;
    private Image image;

    public Position<Creature> getPosition() {
        return position;
    }

    public void setPosition(Position<Creature> position) {
        this.position = position;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}