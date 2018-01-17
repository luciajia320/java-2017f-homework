package tangwy.nju.edu.cn.huluwavsdemons;

import tangwy.nju.edu.cn.huluwasvsdemons.GUIWindow;
import org.junit.Test;
import static org.junit.Assert.*;
public class TestGUIWindow {
    @Test
    public void testGetInstance() throws Exception{
        assertNotEquals(GUIWindow.getInstance(),null);
        assertEquals(GUIWindow.getInstance(),GUIWindow.getInstance());
    }
}
