package entity;

import MyException.OutOfBounce;
import org.junit.Before;
import org.junit.Test;

public class OutOfBounceTest {
    Ground ground;
    @Before
    public void setUp() throws Exception {
        ground = new Ground();
    }

//    @Test(expected = OutOfBounce.class)
    @Test
    public void testOutOfBounceException() {
        ground.getCreatures().add(new Huluwa());
    }
}
