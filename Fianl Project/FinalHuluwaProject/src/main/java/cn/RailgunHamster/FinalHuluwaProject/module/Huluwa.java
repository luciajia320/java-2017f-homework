package cn.RailgunHamster.FinalHuluwaProject.module;

import cn.RailgunHamster.FinalHuluwaProject.gui.Materials;
import cn.RailgunHamster.FinalHuluwaProject.gui.Position;

class Huluwa extends Unit {
    Huluwa(String name, Position Position) {
        super(name, Position, new Materials().getImage("葫芦娃" + name, Materials.getUnitSize()));
    }

    @Override
    public Camp camp() {
        return Camp.Huluwa;
    }
}
