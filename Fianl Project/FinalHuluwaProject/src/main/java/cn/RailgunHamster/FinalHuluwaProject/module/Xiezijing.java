package cn.RailgunHamster.FinalHuluwaProject.module;

import cn.RailgunHamster.FinalHuluwaProject.gui.Position;

class Xiezijing extends Unit {
    Xiezijing(String name, Position Position) {
        super(name, Position);
    }

    @Override
    public Camp camp() {
        return Camp.Monster;
    }
}
