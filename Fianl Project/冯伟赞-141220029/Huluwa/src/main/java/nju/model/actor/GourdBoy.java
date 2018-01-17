package nju.model.actor;

import nju.constant.Values;
import nju.view.Field;


public class GourdBoy extends Actor {

    public GourdBoy(int x, int y,  Field field) {
        super(x, y, field);
        setImageByResourceFile("img/huluwa.png");
        setName(Values.str.GOURD_BOY);
        setStrength(20);
        setType(Values.id.GOURD_BOY);
        setTeamId(1);

    }

}
