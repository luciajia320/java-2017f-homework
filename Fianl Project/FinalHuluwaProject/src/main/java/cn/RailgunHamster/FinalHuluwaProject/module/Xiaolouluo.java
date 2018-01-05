package cn.RailgunHamster.FinalHuluwaProject.module;

import cn.RailgunHamster.FinalHuluwaProject.gui.Position;

public class Xiaolouluo extends Unit {
    Xiaolouluo(String name, Position Position) {
        super(name, Position);
    }

    @Override
    public Camp camp() {
        return Camp.Monster;
    }
}
