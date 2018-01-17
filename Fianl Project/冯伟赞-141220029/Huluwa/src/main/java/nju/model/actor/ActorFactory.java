package nju.model.actor;

import nju.constant.Values;
import nju.view.Field;

public class ActorFactory {
    public Actor createActor(int ActorId, int x, int y, Field field) {
        switch (ActorId) {
            case Values.id.GOURD_BOY:
                return new GourdBoy(x, y, field);
            case Values.id.GRANDPA:
                return new Grandpa(x, y, field);
            case Values.id.SNAKE:
                return new Snake(x, y, field);
            case Values.id.SCORPION:
                return new Scorpion(x, y, field);
            case Values.id.CREEP:
                return new Creep(x, y, field);
            default:
                return null;
        }

    }
}
