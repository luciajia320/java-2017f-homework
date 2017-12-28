package space;

import creature.GrandPa;
import creature.Minion;
import creature.ScorpionEssence;
import creature.SnakeEssence;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpaceTest {
    private static Space space = new Space(11);
    private GrandPa gp = new GrandPa();
    private Minion mn = new Minion();
    private ScorpionEssence se = new ScorpionEssence();
    private SnakeEssence sn = new SnakeEssence();
    private String scene;

    @Before
    public void setUp() throws Exception {
        space.creature_position_setter(gp, 2, 2);
        space.creature_position_setter(mn, 3, 3);
        space.creature_position_setter(se, 4, 4);
        space.creature_position_setter(sn, 5, 5);

        scene = "[] [] [] [] [] [] [] [] [] [] [] \n" +
                "[] [] [] [] [] [] [] [] [] [] [] \n" +
                "[] [] 爷 [] [] [] [] [] [] [] [] \n" +
                "[] [] [] 卒 [] [] [] [] [] [] [] \n" +
                "[] [] [] [] 蝎 [] [] [] [] [] [] \n" +
                "[] [] [] [] [] 蛇 [] [] [] [] [] \n" +
                "[] [] [] [] [] [] [] [] [] [] [] \n" +
                "[] [] [] [] [] [] [] [] [] [] [] \n" +
                "[] [] [] [] [] [] [] [] [] [] [] \n" +
                "[] [] [] [] [] [] [] [] [] [] [] \n" +
                "[] [] [] [] [] [] [] [] [] [] [] \n";
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

    @Test
    public void test_toString() throws Exception {
        assertEquals(scene, space.toString());
    }
}