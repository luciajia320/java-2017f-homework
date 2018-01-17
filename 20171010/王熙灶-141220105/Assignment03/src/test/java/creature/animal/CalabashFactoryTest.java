package creature.animal;

import creature.animal.CalaCrops;
import creature.animal.CalabashFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalabashFactoryTest {
    private CalabashFactory calabashFactory = CalabashFactory.getInstance();
    private CalaCrops crops = CalaCrops.getInstance();

    @Test
    public void test_getInstance() throws Exception {
        CalabashFactory cf = CalabashFactory.getInstance();
        assertEquals(cf, calabashFactory);
    }

    @Test
    public void test_get() throws Exception {
        for(int i = 0; i < 7; i++) {
            assertEquals(calabashFactory.get(i), crops.get(i));
        }
    }
}