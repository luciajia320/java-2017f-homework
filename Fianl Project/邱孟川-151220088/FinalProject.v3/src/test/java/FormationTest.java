package test.java;
import main.java.nju.java.Formation;
import main.java.nju.java.Position;
import main.java.nju.java.SnakeFormation;
import main.java.nju.java.WingFormation;
import org.junit.Test;
import static org.junit.Assert.*;

public class FormationTest {
    @Test
    public void testisCrossBound(){
        assertEquals(true, new SnakeFormation(new Position(2,11)).isCrossBound(10));
        assertEquals(false, new WingFormation(new Position(6, 1)).isCrossBound(10));
    }

    @Test
    public void testContains(){
        assertEquals(true, new WingFormation(new Position(0,0)).contains(new SnakeFormation(new Position(0,0))));
    }
    @Test
    public void testOverlap(){
        assertEquals(true, new WingFormation(new Position(0,0)).overlap(new WingFormation(new Position(0,2))));
    }

}
