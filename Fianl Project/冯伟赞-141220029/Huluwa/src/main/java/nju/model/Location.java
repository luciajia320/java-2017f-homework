package nju.model;

import java.util.Objects;

public class Location {
    private int x = 0;
    private int y = 0;

    public Location() {
    }

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setX(int x, int lowerBound, int upperBound) {
        if (x < lowerBound) {
            setX(lowerBound);
        } else if (x > upperBound) {
            setX(upperBound);
        } else {
            setX(x);
        }
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setY(int y, int lowerBound, int upperBound) {
        if (y < lowerBound) {
            setY(lowerBound);
        } else if (y > upperBound) {
            setY(upperBound);
        } else {
            setY(y);
        }
    }

    public void update(int x, int y) {
        setX(x);
        setY(y);
    }

    public void update(int x, int y, int minX, int maxX, int minY, int maxY) {
        setX(x, minX, maxX);
        setY(y, minY, maxY);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return getX() == location.getX() &&
                getY() == location.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }

    @Override
    public String toString() {
        return "(" +
                 + x +
                ", " + y +
                ')';
    }
}
