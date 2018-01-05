package cn.RailgunHamster.FinalHuluwaProject.gui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {
    private Position position1, position2;

    @BeforeEach
    void setUp() {
        position1 = new Position(4, 4);
        position2 = new Position(20, 20);
    }

    @Test
    void distanceTo() {
        assertEquals((Integer) 32, position1.distanceTo(position2));
    }

    @Test
    void location() {
        assertEquals((Integer) (Materials.col * 4 + 4), position1.location());
        assertEquals((Integer) (Materials.col * 20 + 20), position2.location());
    }

    @Test
    void stepTo() {
        assertEquals(new Position(3, 4), position1.stepTo(Position.Direction.up, 1));
        assertEquals(new Position(2, 4), position1.stepTo(Position.Direction.up, 2));
        assertEquals(new Position(5, 4), position1.stepTo(Position.Direction.down, 1));
        assertEquals(new Position(4, 3), position1.stepTo(Position.Direction.left, 1));
        assertEquals(new Position(4, 5), position1.stepTo(Position.Direction.right, 1));
        assertEquals(new Position(20,20), position2.stepTo(Position.Direction.up, 1));

        assertEquals(new Position(0, 4), new Position(0, 4).stepTo(Position.Direction.up, 1));
    }

    @Test
    void legal() {
        assertEquals(true, position1.legal());
        assertEquals(false, position2.legal());
    }

    @Test
    void compareTo() {
        assertEquals(-1, position1.compareTo(position2));
        assertEquals(1, position2.compareTo(position1));
        assertEquals(0, position1.compareTo(position1));
    }

    @Test
    void equals() {
        assertEquals(false, position1.equals(position2));
    }

}