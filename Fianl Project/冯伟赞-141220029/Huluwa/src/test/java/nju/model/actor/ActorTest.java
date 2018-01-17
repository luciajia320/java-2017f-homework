package nju.model.actor;

import nju.model.ActionBean;
import nju.view.Field;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ActorTest {

    private Field field;

    @Before
    public void setUp() throws Exception {
        field = new Field();
    }

    @Test
    public void move() {
        Actor actor = new Actor(0, 0, field);
        actor.move(1, 0);
        assertEquals(actor.getLocation().getX(), 1);
    }
}