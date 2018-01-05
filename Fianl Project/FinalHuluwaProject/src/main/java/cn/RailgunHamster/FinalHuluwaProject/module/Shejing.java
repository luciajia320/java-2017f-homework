package cn.RailgunHamster.FinalHuluwaProject.module;

import cn.RailgunHamster.FinalHuluwaProject.gui.Materials;
import cn.RailgunHamster.FinalHuluwaProject.gui.Position;

import java.util.List;

public class Shejing extends Manager {
    private final static String name = "蛇精";
    private final static Integer right = (int) Materials.col;
    private final static Integer left = right / 2;

    public Shejing(Position Position) {
        super(name, Position, new Position(left, right));
    }

    public Shejing(Position Position, String formation) {
        super(name, Position, formation, new Position(left, right));
    }

    @Override
    public void addChildren() {
        List<Position> empty = this.map.getEmptyPositions(6);
        this.addManagement(new Xiezijing("蝎子精", empty.get(0)));
        for (int i = 1;i < empty.size();i++) {
            this.addManagement(new Xiaolouluo("小喽啰" + i, empty.get(i)));
        }
    }

    @Override
    public Camp camp() {
        return Camp.Monster;
    }
}
