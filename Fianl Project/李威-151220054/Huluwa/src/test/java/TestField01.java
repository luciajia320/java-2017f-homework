
import junit.framework.TestCase;

public class TestField01 extends TestCase {

    public void testGetBoardHeight() {
        Field field=new Field(null);
        assertEquals(350, field.getBoardHeight());
    }

}
