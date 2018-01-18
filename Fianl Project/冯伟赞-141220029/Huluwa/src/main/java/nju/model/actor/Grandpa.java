package nju.model.actor;

import nju.constant.Values;
import nju.view.Field;

public class Grandpa extends Actor {
    public Grandpa(int x, int y, Field field) {
        super(x, y, field);
        setImageByResourceFile("img/grandpa.png");
        setName(Values.str.GRANDPA);
        setStrength(14);
        setType(Values.id.GRANDPA);

        setTeamId(1);

    }
}
