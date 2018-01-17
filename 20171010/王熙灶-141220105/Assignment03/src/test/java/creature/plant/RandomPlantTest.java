package creature.plant;

import org.junit.Test;

import static org.junit.Assert.*;

public class RandomPlantTest {
    @Test
    public void test_get() throws Exception {
        for(int i = 0; i < 10; i++) {
            assertNotEquals(null, RandomPlant.get());
        }
    }
}