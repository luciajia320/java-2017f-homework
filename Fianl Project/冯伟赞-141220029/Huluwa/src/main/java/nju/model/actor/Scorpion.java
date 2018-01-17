package nju.model.actor;

import nju.constant.Values;
import nju.view.Field;

public class Scorpion extends Actor {

    public Scorpion(int x, int y, Field field) {
        super(x, y, field);
        setImageByResourceFile("img/Scorpion.png");
        setName(Values.str.SCORPION);
        setType(Values.id.SCORPION);

        setStrength(21);
        setTeamId(2);

    }
}
