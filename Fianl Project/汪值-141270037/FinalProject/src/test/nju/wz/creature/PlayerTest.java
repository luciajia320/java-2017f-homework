package nju.wz.creature;

import nju.wz.position.Field;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

    private static Player player = new Huluwa(null,0);
    @Test
    public void index2() throws Exception {
        assertEquals(1, player.index1(100));
    }

    @Test
    public void index1() throws Exception {
        assertEquals(0, player.index1(Field.OFFSET));
    }

}