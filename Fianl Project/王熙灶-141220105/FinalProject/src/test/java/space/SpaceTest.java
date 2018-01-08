package space;

import creature.animal.GrandPa;
import creature.animal.Minion;
import creature.animal.ScorpionEssence;
import creature.animal.SnakeEssence;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

public class SpaceTest {
    private static Space space = new Space(12, 9);
    private GrandPa gp = GrandPa.getInstance();
    private ScorpionEssence se = ScorpionEssence.getInstance();
    private SnakeEssence sn = SnakeEssence.getInstance();

    @Before
    public void setUp() throws Exception {
        space.bind(gp, 2, 2);
        space.bind(se, 4, 4);
        space.bind(sn, 5, 5);
    }

    @Test
    public void test_bind() throws Exception {
        assertEquals(gp, space.getPos(2, 2).getHolder());
        assertEquals(se, space.getPos(4, 4).getHolder());
        assertEquals(sn, space.getPos(5, 5).getHolder());

        assertEquals(space.getPos(2, 2), space.getPos(2, 2).getHolder().getPosition());
        assertEquals(space.getPos(4, 4), space.getPos(4, 4).getHolder().getPosition());
        assertEquals(space.getPos(5, 5), space.getPos(5, 5).getHolder().getPosition());
    }

    @Test
    public void test_unbindAll() throws Exception {
        space.unbindAll();

        assertEquals(null, space.getPos(2, 2).getHolder());
        assertEquals(null, space.getPos(4, 4).getHolder());
        assertEquals(null, space.getPos(5, 5).getHolder());

        assertEquals(null, gp.getPosition());
        assertEquals(null, se.getPosition());
        assertEquals(null, sn.getPosition());
    }

    @Test(expected = Exception.class)
    public void test_getPosA() throws Exception {
        space.getPos(-11, 5);
        fail("getPos参数为负数没有抛出异常");
    }

    @Test(expected = Exception.class)
    public void test_getPosB() throws Exception {
        space.getPos(11, -5);
        fail("getPos参数为负数没有抛出异常");
    }

    @Test(expected = Exception.class)
    public void test_getPosC() throws Exception {
        space.getPos(21, 5);
        fail("getPos参数超出边界没有抛出异常");
    }

    @Test(expected = Exception.class)
    public void test_getPosD() throws Exception {
        space.getPos(11, 15);
        fail("getPos参数超出边界没有抛出异常");
    }
}