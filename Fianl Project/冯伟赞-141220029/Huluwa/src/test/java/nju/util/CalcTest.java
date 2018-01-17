package nju.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalcTest {

    @Test
    public void distanceSquare() {
        assertTrue(Calc.distanceSquare(1, 1, 2, 2) == 2);
        assertTrue(Calc.distanceSquare(0, 1, 3, 2) == (9 + 1));
    }
}