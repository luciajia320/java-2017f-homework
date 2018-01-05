package cn.RailgunHamster.FinalHuluwaProject.module;

import cn.RailgunHamster.FinalHuluwaProject.gui.Materials;
import cn.RailgunHamster.FinalHuluwaProject.gui.Pair;
import cn.RailgunHamster.FinalHuluwaProject.gui.Position;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理员
 */
public abstract class Manager extends Unit implements Formation {
    /**
     * 所管理的单位
     */
    private List<Unit> management = new ArrayList<>();
    /**
     * 阵型
     */
    private FormationImage formationImage;
    /**
     * 在地图上的相对位置
     */
    private Integer left, right;

    Manager(String name, Position Position, Position offset) {
        super(name, Position);
        this.left = offset.getRow();
        this.right = offset.getCol();
        this.management.add(this);
    }

    Manager(String name, Position Position, String formation, Position offset) {
        this(name, Position, offset);
        this.setFormation(formation);
    }

    void addManagement(Unit unit) {
        if (unit == this) return;
        management.add(unit);
        map.addUnit(unit);
    }

    /**
     * 添加管理的单位
     */
    public abstract void addChildren();

    @Override
    public void setFormation(String name) {
        Dimension size = new Dimension(Materials.col, Materials.row);
        size = new Dimension(size.width - (this.right - this.left), size.height);
        this.formationImage = new FormationImage(name, size);
    }

    @Override
    public void formation() {
        if (formationImage == null) return;
        int count = 0;
        for (Pair<Double, Position> pair : formationImage.read()) {
            if (count >= management.size()) break;
            Position Position = pair.getValue();
            management.get(count++).moveTo(new Position(Position.getRow(), Position.getCol() + this.left));
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", offset(" + left + ", " + right + ")";
    }
}
