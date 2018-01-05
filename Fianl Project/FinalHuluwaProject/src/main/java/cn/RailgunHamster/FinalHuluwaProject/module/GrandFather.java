package cn.RailgunHamster.FinalHuluwaProject.module;

import cn.RailgunHamster.FinalHuluwaProject.gui.Materials;
import cn.RailgunHamster.FinalHuluwaProject.gui.Position;

import java.util.List;

public class GrandFather extends Manager {
    private final static String name = "爷爷";
    private final static Integer left = 0;
    private final static Integer right = (int) (Materials.col / 2);

    public GrandFather(Position Position) {
        super(name, Position, new Position(left, right));
    }

    public GrandFather(Position Position, String formation) {
        super(name, Position, formation, new Position(left, right));
    }

    private final static String huluwaName = "大二三四五六七";

    @Override
    public void addChildren() {
        List<Position> empty = this.map.getEmptyPositions(7);
        for (int i = 0;i < empty.size();i++) {
            this.addManagement(new Huluwa("老" + huluwaName.charAt(i), empty.get(i)));
        }
    }

    @Override
    public Camp camp() {
        return Camp.Huluwa;
    }
}
