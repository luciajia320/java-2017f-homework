package UI;

import Position.Position;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Thing2Dtest {

    @Test
    public void testGetX()
    {
        assertEquals(1,new Thing2D(1,1,new Position(5,5)).getX());
    }
}
