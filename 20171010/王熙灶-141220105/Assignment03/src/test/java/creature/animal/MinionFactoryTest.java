package creature.animal;

import creature.animal.EssenceCrops;
import creature.animal.MinionFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class MinionFactoryTest {
    private MinionFactory minionFactory = MinionFactory.getInstance();
    private EssenceCrops crops = EssenceCrops.getInstance();

    @Test
    public void test_getInstance() throws Exception {
        MinionFactory mf = MinionFactory.getInstance();
        assertEquals(mf, minionFactory);
    }

    @Test
    public void test_get() throws Exception {
        for(int i = 0; i < 6; i++) {
            assertEquals(minionFactory.get(i), crops.get(i));
        }
    }
}