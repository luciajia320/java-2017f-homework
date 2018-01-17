package entity;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CreatureTest {
    Ground ground;
    List<Creature> creatures;
    @Before
    public void setUp() throws Exception {
        ground = new Ground();
        creatures = ground.getCreatures();
    }

    @Test
    public void setPoint() throws Exception {
        assertEquals(3, ground.grandpa.getY());
        assertEquals(14, ground.snake.getX());
    }

    @Test
    public void distance() throws Exception {
        assertEquals(36, ground.snake.distance(ground.scorpion));
    }

    @Test
    public void conflict() throws Exception {
        assertEquals(false, ground.grandpa.conflict(ground.getCreatures().get(1)));
    }
}
