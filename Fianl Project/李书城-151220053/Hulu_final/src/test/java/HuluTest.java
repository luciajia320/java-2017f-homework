import org.junit.Test;
import static org.junit.Assert.*;

public class HuluTest {
    @Test
    public void blood() throws Exception {
        assertEquals(new Huluwa(1,1,1,1, new Fight(),"").getBlood(), 1000);
    }

    @Test
    public void testFactorial() throws Exception {
        Huluwa h = new Huluwa(1,1,1,1, new Fight(),"");
        h.getWounded(50);
        assertNotEquals(h.getBlood(),1000);
    }
}




