package creature.animal;

import creature.animal.CalaCrops;
import formation.LongSnake;
import org.junit.Before;
import org.junit.Test;
import space.Space;

import static org.junit.Assert.*;

public class CropsTest {
    private CalaCrops crops = CalaCrops.getInstance();
    private Space space = new Space(11);

    @Before
    public void setUp() throws Exception {
        crops.setFormation(new LongSnake(space, 3, 3));
    }

    @Test
    public void clearFormation() throws Exception {
        crops.clearFormation();

        for(int i = 0; i < 7; i++) {
            assertEquals(null, crops.get(i).getPosition());
            assertNotEquals(null, space.getPos(i+3, 3).getHolder());
        }
    }
}