package nju.model.actor;

import nju.constant.Values;
import nju.view.Field;

public class Snake extends Actor {
    public Snake(int x, int y, Field field) {
        super(x, y, field);
        setImageByResourceFile("img/Snake.png");
        setName(Values.str.SNAKE);
        setType(Values.id.SNAKE);
        setStrength(22);
        setTeamId(2);

    }
}
