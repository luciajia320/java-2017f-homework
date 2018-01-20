package nju.model;

import java.util.Objects;

public class ActionBean {
    private int id;
    private boolean isAlive;
    private int type;
    private boolean isStarted;
    private int x, y;

    public ActionBean(int id, boolean isAlive, int type, boolean isStarted, int x, int y) {
        this.id = id;
        this.isAlive = isAlive;
        this.type = type;
        this.isStarted = isStarted;
        this.x = x;
        this.y = y;
    }

    public ActionBean() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActionBean that = (ActionBean) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId());
    }
}
