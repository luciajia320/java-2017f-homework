package entity;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static common.Constant.*;
import static org.junit.Assert.*;

public class FormationTest {
    Ground ground;
    ArrayList<Creature> brothers, lackeys;
    Formation gooseSwing, xFormation;
    @Before
    public void setUp() throws Exception {
        ground = new Ground();
        brothers = ground.getBrothers();
        lackeys = ground.getLackeys();
        gooseSwing = new GooseSwing(new Location(0, 0), brothers);
        xFormation = new XFormation(new Location(GROUNDWIDTH - XWIDTH, 0), lackeys);
    }

    // 因为角色的坐标是在阵型设定时设定的, 如果葫芦娃的坐标对了, 那么阵型就对了
    @Test
    public void testGooseSwing() {
        Creature cre4 = brothers.get(3);
        Creature cre5 = brothers.get(4);
        assertEquals(3, cre4.getY());
        assertEquals(2, cre5.getX());
    }
    @Test
    public void testXFormation() {
        Creature lackey1 = lackeys.get(0);
        Creature lackey2 = lackeys.get(2);
        assertEquals(13, lackey1.getX());
        assertEquals(13, lackey2.getX());
        assertEquals(2, lackey2.getY());
    }
}
