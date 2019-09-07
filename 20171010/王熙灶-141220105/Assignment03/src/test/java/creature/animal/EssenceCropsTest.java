package creature.animal;

import creature.animal.EssenceCrops;
import formation.*;
import org.junit.Test;
import space.Space;

import static org.junit.Assert.*;

public class EssenceCropsTest {
    private EssenceCrops crops = EssenceCrops.getInstance();
    private Space space = new Space(11);

    @Test
    public void getInstance() throws Exception {
        EssenceCrops c = EssenceCrops.getInstance();
        assertEquals(c, crops);
    }

    @Test
    public void test_setLongSnakeFormation() throws Exception {
        int current_x = 3, current_y = 3;
        crops.setFormation(new LongSnake(space, current_x, current_y));

        assertEquals(crops.getScorpionEssence(), space.getPos(current_x, current_y).getHolder());
        assertEquals(crops.getScorpionEssence().getPosition(), space.getPos(current_x, current_y));

        assertEquals(crops.get(0), space.getPos(current_x+1, current_y).getHolder());
        assertEquals(crops.get(0).getPosition(), space.getPos(current_x+1, current_y));

        assertEquals(crops.get(1), space.getPos(current_x+2, current_y).getHolder());
        assertEquals(crops.get(1).getPosition(), space.getPos(current_x+2, current_y));

        assertEquals(crops.get(2), space.getPos(current_x+3, current_y).getHolder());
        assertEquals(crops.get(2).getPosition(), space.getPos(current_x+3, current_y));

        assertEquals(crops.get(3), space.getPos(current_x+4, current_y).getHolder());
        assertEquals(crops.get(3).getPosition(), space.getPos(current_x+4, current_y));

        assertEquals(crops.get(4), space.getPos(current_x+5, current_y).getHolder());
        assertEquals(crops.get(4).getPosition(), space.getPos(current_x+5, current_y));

        assertEquals(crops.get(5), space.getPos(current_x+6, current_y).getHolder());
        assertEquals(crops.get(5).getPosition(), space.getPos(current_x+6, current_y));
    }

    @Test
    public void test_setHeYiFormation() throws Exception {
        int current_x = 5, current_y = 5;
        crops.setFormation(new HeYi(space, current_x, current_y));

        assertEquals(crops.getScorpionEssence(), space.getPos(current_x, current_y).getHolder());
        assertEquals(crops.getScorpionEssence().getPosition(), space.getPos(current_x, current_y));

        assertEquals(crops.get(0), space.getPos(current_x-1, current_y+1).getHolder());
        assertEquals(crops.get(0).getPosition(), space.getPos(current_x-1, current_y+1));

        assertEquals(crops.get(1), space.getPos(current_x+1, current_y+1).getHolder());
        assertEquals(crops.get(1).getPosition(), space.getPos(current_x+1, current_y+1));

        assertEquals(crops.get(2), space.getPos(current_x-2, current_y+2).getHolder());
        assertEquals(crops.get(2).getPosition(), space.getPos(current_x-2, current_y+2));

        assertEquals(crops.get(3), space.getPos(current_x+2, current_y+2).getHolder());
        assertEquals(crops.get(3).getPosition(), space.getPos(current_x+2, current_y+2));

        assertEquals(crops.get(4), space.getPos(current_x-3, current_y+3).getHolder());
        assertEquals(crops.get(4).getPosition(), space.getPos(current_x-3, current_y+3));

        assertEquals(crops.get(5), space.getPos(current_x+3, current_y+3).getHolder());
        assertEquals(crops.get(5).getPosition(), space.getPos(current_x+3, current_y+3));
    }

    @Test
    public void test_setFengShiFormation() throws Exception {
        int current_x = 5, current_y = 5;
        crops.setFormation(new FengShi(space, current_x, current_y));

        assertEquals(crops.getScorpionEssence(), space.getPos(current_x, current_y).getHolder());
        assertEquals(crops.getScorpionEssence().getPosition(), space.getPos(current_x, current_y));

        assertEquals(crops.get(0), space.getPos(current_x-1, current_y+1).getHolder());
        assertEquals(crops.get(0).getPosition(), space.getPos(current_x-1, current_y+1));

        assertEquals(crops.get(1), space.getPos(current_x, current_y+1).getHolder());
        assertEquals(crops.get(1).getPosition(), space.getPos(current_x, current_y+1));

        assertEquals(crops.get(2), space.getPos(current_x+1, current_y+1).getHolder());
        assertEquals(crops.get(2).getPosition(), space.getPos(current_x+1, current_y+1));

        assertEquals(crops.get(3), space.getPos(current_x, current_y+2).getHolder());
        assertEquals(crops.get(3).getPosition(), space.getPos(current_x, current_y+2));

        assertEquals(crops.get(4), space.getPos(current_x, current_y+3).getHolder());
        assertEquals(crops.get(4).getPosition(), space.getPos(current_x, current_y+3));

        assertEquals(crops.get(5), space.getPos(current_x, current_y+4).getHolder());
        assertEquals(crops.get(5).getPosition(), space.getPos(current_x, current_y+4));
    }

    @Test
    public void test_setFangYuanFormation() throws Exception {
        int current_x = 5, current_y = 5;
        crops.setFormation(new FangYuan(space, current_x, current_y));

        assertEquals(crops.getScorpionEssence(), space.getPos(current_x, current_y).getHolder());
        assertEquals(crops.getScorpionEssence().getPosition(), space.getPos(current_x, current_y));

        assertEquals(crops.get(0), space.getPos(current_x-1, current_y+1).getHolder());
        assertEquals(crops.get(0).getPosition(), space.getPos(current_x-1, current_y+1));

        assertEquals(crops.get(1), space.getPos(current_x+1, current_y+1).getHolder());
        assertEquals(crops.get(1).getPosition(), space.getPos(current_x+1, current_y+1));

        assertEquals(crops.get(2), space.getPos(current_x-2, current_y+2).getHolder());
        assertEquals(crops.get(2).getPosition(), space.getPos(current_x-2, current_y+2));

        assertEquals(crops.get(3), space.getPos(current_x+2, current_y+2).getHolder());
        assertEquals(crops.get(3).getPosition(), space.getPos(current_x+2, current_y+2));

        assertEquals(crops.get(4), space.getPos(current_x-1, current_y+3).getHolder());
        assertEquals(crops.get(4).getPosition(), space.getPos(current_x-1, current_y+3));

        assertEquals(crops.get(5), space.getPos(current_x+1, current_y+3).getHolder());
        assertEquals(crops.get(5).getPosition(), space.getPos(current_x+1, current_y+3));
    }

    @Test
    public void test_setDongEFormation() throws Exception {
        int current_x = 4, current_y = 4;
        crops.setFormation(new DongE(space, current_x, current_y));

        assertEquals(crops.getScorpionEssence(), space.getPos(current_x, current_y).getHolder());
        assertEquals(crops.getScorpionEssence().getPosition(), space.getPos(current_x, current_y));

        assertEquals(crops.get(0), space.getPos(current_x+1, current_y+1).getHolder());
        assertEquals(crops.get(0).getPosition(), space.getPos(current_x+1, current_y+1));

        assertEquals(crops.get(1), space.getPos(current_x, current_y+2).getHolder());
        assertEquals(crops.get(1).getPosition(), space.getPos(current_x, current_y+2));

        assertEquals(crops.get(2), space.getPos(current_x+1, current_y+3).getHolder());
        assertEquals(crops.get(2).getPosition(), space.getPos(current_x+1, current_y+3));

        assertEquals(crops.get(3), space.getPos(current_x, current_y+4).getHolder());
        assertEquals(crops.get(3).getPosition(), space.getPos(current_x, current_y+4));

        assertEquals(crops.get(4), space.getPos(current_x+1, current_y+5).getHolder());
        assertEquals(crops.get(4).getPosition(), space.getPos(current_x+1, current_y+5));

        assertEquals(crops.get(5), space.getPos(current_x, current_y+6).getHolder());
        assertEquals(crops.get(5).getPosition(), space.getPos(current_x, current_y+6));
    }
}