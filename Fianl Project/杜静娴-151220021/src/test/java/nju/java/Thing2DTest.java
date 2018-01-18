package nju.java;

import org.junit.Test;

import static org.junit.Assert.*;

public class Thing2DTest {
    @Test
    public void setImage() throws Exception {
        Thing2D x = new Thing2D(1,2);
        assertNotEquals(0,x.x());
    }

}