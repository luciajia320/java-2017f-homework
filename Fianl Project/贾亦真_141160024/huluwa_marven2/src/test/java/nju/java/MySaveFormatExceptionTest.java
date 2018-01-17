package nju.java;

import org.junit.Test;

public class MySaveFormatExceptionTest {
    @Test
    public void printStackTrace() throws Exception {
        MySaveFormatException e = new MySaveFormatException("line length incorrect: " + 2);
        e.printStackTrace();
    }

}