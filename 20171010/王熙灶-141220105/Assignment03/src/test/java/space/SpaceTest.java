package space;

import creature.animal.GrandPa;
import creature.animal.Minion;
import creature.animal.ScorpionEssence;
import creature.animal.SnakeEssence;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpaceTest {
    private static Space space = new Space(11);
    private GrandPa gp = GrandPa.getInstance();
    private Minion mn = new Minion();
    private ScorpionEssence se = ScorpionEssence.getInstance();
    private SnakeEssence sn = SnakeEssence.getInstance();

    @Before
    public void setUp() throws Exception {
        space.creature_position_setter(gp, 2, 2);
        space.creature_position_setter(mn, 3, 3);
        space.creature_position_setter(se, 4, 4);
        space.creature_position_setter(sn, 5, 5);
    }

    @Test
    public void test_creature_position_setter() throws Exception {
        assertEquals(gp, space.getPos(2, 2).getHolder());
        assertEquals(mn, space.getPos(3, 3).getHolder());
        assertEquals(se, space.getPos(4, 4).getHolder());
        assertEquals(sn, space.getPos(5, 5).getHolder());

        assertEquals(space.getPos(2, 2), space.getPos(2, 2).getHolder().getPosition());
        assertEquals(space.getPos(3, 3), space.getPos(3, 3).getHolder().getPosition());
        assertEquals(space.getPos(4, 4), space.getPos(4, 4).getHolder().getPosition());
        assertEquals(space.getPos(5, 5), space.getPos(5, 5).getHolder().getPosition());
    }
}