package cn.RailgunHamster.FinalHuluwaProject.gui;

import java.awt.*;
import java.io.Serializable;

/**
 * 表达一个点的坐标
 */
public class Position implements Comparable<Position>, Serializable {
    private Pair<Integer, Integer> position;

    public Position(Integer row, Integer col) {
        this.position = new Pair<>(row, col);
    }

    public Position(Position Position) {
        this(Position.getRow(), Position.getCol());
    }

    public Integer getRow() {
        return position.getKey();
    }

    public Integer getCol() {
        return position.getValue();
    }

    /**
     * @param other 另一个位置
     * @return 计算两个位置间的距离，距离为dx + dy
     */
    public Integer distanceTo(Position other) {
        return Math.abs(getRow() - other.getRow()) + Math.abs(getCol() - other.getCol());
    }

    /**
     * @return 获取该位置在地图上的排序顺位
     */
    public Integer location() {
        return getRow() * size.width + getCol();
    }

    public Position stepTo(Direction direction, Integer step) {
        Position dst = this;
        switch (direction) {
            case up:
                dst = new Position(getRow() - step, getCol());
                break;
            case down:
                dst = new Position(getRow() + step, getCol());
                break;
            case left:
                dst = new Position(getRow(), getCol() - step);
                break;
            case right:
                dst = new Position(getRow(), getCol() + step);
                break;
        }
        if (dst.legal()) return dst;
        else return new Position(this);
    }

    public enum Direction {
        up, down, left, right;
    }

    /**
     * @return 判断坐标是否合法
     */
    public boolean legal() {
        return getCol() >= 0 && getCol() < size.width && getRow() >= 0 && getRow() < size.getHeight();
    }

    @Override
    public String toString() {
        return "Position(" + position.getKey() + ", " + position.getValue() + ")";
    }

    private static Dimension size = new Dimension(Materials.col, Materials.row);

    @Override
    public int compareTo(Position o) {
        Integer me = location();
        Integer other = o.location();
        if (me < other) return -1;
        else if (me > other) return 1;
        else return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Position)) return super.equals(obj);
        return location().equals(((Position) obj).location());
    }
}
