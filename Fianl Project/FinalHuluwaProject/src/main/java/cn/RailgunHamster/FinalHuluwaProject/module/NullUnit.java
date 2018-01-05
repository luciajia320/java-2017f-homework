package cn.RailgunHamster.FinalHuluwaProject.module;

import cn.RailgunHamster.FinalHuluwaProject.gui.Position;

/**
 * 空单位
 */
class NullUnit extends Unit {
    private static long id = 0;

    NullUnit(Position Position) {
        super("NullUnit" + id++, Position);
    }

    @Override
    public boolean isNull() {
        return true;
    }
}
