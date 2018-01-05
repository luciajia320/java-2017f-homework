package cn.RailgunHamster.FinalHuluwaProject.module;

import cn.RailgunHamster.FinalHuluwaProject.gui.Materials;
import cn.RailgunHamster.FinalHuluwaProject.gui.Position;

import java.awt.*;

/**
 * 可显示物体
 */
public abstract class Mono2D {
    /**
     * 位置
     */
    private Position position;
    /**
     * 样貌
     */
    private Image image;

    public Mono2D(Position position) {
        this.position = position;
        this.image = new Materials().getImage(this.getClass(), Materials.getUnitSize());
    }

    public Mono2D(Position Position, Image image) {
        this.position = Position;
        this.image = image;
    }

    private Mono2D() {}

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return new Position(this.position);
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return getClass().getName() + " : " + position;
    }
}
