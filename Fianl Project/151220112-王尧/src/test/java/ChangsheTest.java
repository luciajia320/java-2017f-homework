import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChangsheTest {
    @BeforeClass
    public static void beforeTest() {
        System.out.println("Test begins");
    }
    @AfterClass
    public static void afterTest() {
        System.out.println("Test ends");
    }
    @Test
    public void testBorder() {
        Field field = new Field(9, 16);
        Position position = new Position(1, 0);
        Changshe c = new Changshe();
        c.form(7, position, field);
        boolean flag = c.getX() < 0 || c.getX() >= c.getBorderX() || c.getY() < 0 || c.getY() >= c.getBorderY();
        assert (flag);
    }
}