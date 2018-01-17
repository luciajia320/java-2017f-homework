package nju.model.actor;

import nju.constant.Values;
import nju.view.Field;

public class Creep extends Actor {

    public Creep(int x, int y, Field field) {
        super(x, y, field);
        setImageByResourceFile("img/creep.png");
        setName(Values.str.CREEP);
        setType(Values.id.CREEP);

        setStrength(12);
        setTeamId(2);
    }
}
