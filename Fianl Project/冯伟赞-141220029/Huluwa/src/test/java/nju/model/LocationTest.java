package nju.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class LocationTest {

    @Test
    public void update() {
        Location loc = new Location();
        loc.update(10, 20);
        assertEquals(loc.getX(), 10);
        assertEquals(loc.getY(), 20);
        loc.update(20, 30, 0, 15, 0, 40);
        assertEquals(loc.getX(), 15);
        loc.update(-10, 20, 0, 10, -20, 100);
        assertEquals(loc.getX(), 0);

    }
}