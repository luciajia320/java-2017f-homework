package creature;

import formation.*;
import org.junit.Before;
import org.junit.Test;
import space.Space;

import static org.junit.Assert.*;

public class CalaCropsTest {
    private CalaCrops crops = CalaCrops.getInstance();
    private CalabashFactory calabashFactory = CalabashFactory.getInstance();
    private Space space = new Space(11);

    @Before
    public void setUp() throws Exception {
        crops.shuffle();
    }

    @Test
    public void test_sort() throws Exception {
        crops.sort();

        for(int i = 0; i < 7; i++) {
            assertEquals(calabashFactory.get(i).getColor(), crops.get(i).getColor());
            assertEquals(calabashFactory.get(i).getOrder(), crops.get(i).getOrder());
        }
    }

    @Test
    public void test_setLongSnakeFormation() throws Exception {
        crops.sort();
        crops.setFormation(new LongSnake(space, 3, 3));

        for(int i = 0; i < 7; i++) {
            assertEquals(crops.get(i), space.getPos(i+3, 3).getHolder());
            assertEquals(crops.get(i).getPosition(), space.getPos(i+3, 3));
        }
    }

    @Test
    public void test_setHeYiFormation() throws Exception {
        crops.sort();
        int current_x = 5, current_y = 5;
        crops.setFormation(new HeYi(space, current_x, current_y));

        assertEquals(crops.get(0), space.getPos(current_x, current_y).getHolder());
        assertEquals(crops.get(0).getPosition(), space.getPos(current_x, current_y));

        assertEquals(crops.get(1), space.getPos(current_x-1, current_y+1).getHolder());
        assertEquals(crops.get(1).getPosition(), space.getPos(current_x-1, current_y+1));

        assertEquals(crops.get(2), space.getPos(current_x+1, current_y+1).getHolder());
        assertEquals(crops.get(2).getPosition(), space.getPos(current_x+1, current_y+1));

        assertEquals(crops.get(3), space.getPos(current_x-2, current_y+2).getHolder());
        assertEquals(crops.get(3).getPosition(), space.getPos(current_x-2, current_y+2));

        assertEquals(crops.get(4), space.getPos(current_x+2, current_y+2).getHolder());
        assertEquals(crops.get(4).getPosition(), space.getPos(current_x+2, current_y+2));

        assertEquals(crops.get(5), space.getPos(current_x-3, current_y+3).getHolder());
        assertEquals(crops.get(5).getPosition(), space.getPos(current_x-3, current_y+3));

        assertEquals(crops.get(6), space.getPos(current_x+3, current_y+3).getHolder());
        assertEquals(crops.get(6).getPosition(), space.getPos(current_x+3, current_y+3));
    }

    @Test
    public void test_setFengShiFormation() throws Exception {
        crops.sort();
        int current_x = 5, current_y = 5;
        crops.setFormation(new FengShi(space, current_x, current_y));

        assertEquals(crops.get(0), space.getPos(current_x, current_y).getHolder());
        assertEquals(crops.get(0).getPosition(), space.getPos(current_x, current_y));

        assertEquals(crops.get(1), space.getPos(current_x-1, current_y+1).getHolder());
        assertEquals(crops.get(1).getPosition(), space.getPos(current_x-1, current_y+1));

        assertEquals(crops.get(2), space.getPos(current_x, current_y+1).getHolder());
        assertEquals(crops.get(2).getPosition(), space.getPos(current_x, current_y+1));

        assertEquals(crops.get(3), space.getPos(current_x+1, current_y+1).getHolder());
        assertEquals(crops.get(3).getPosition(), space.getPos(current_x+1, current_y+1));

        assertEquals(crops.get(4), space.getPos(current_x, current_y+2).getHolder());
        assertEquals(crops.get(4).getPosition(), space.getPos(current_x, current_y+2));

        assertEquals(crops.get(5), space.getPos(current_x, current_y+3).getHolder());
        assertEquals(crops.get(5).getPosition(), space.getPos(current_x, current_y+3));

        assertEquals(crops.get(6), space.getPos(current_x, current_y+4).getHolder());
        assertEquals(crops.get(6).getPosition(), space.getPos(current_x, current_y+4));
    }

    @Test
    public void test_setFangYuanFormation() throws Exception {
        crops.sort();
        int current_x = 5, current_y = 5;
        crops.setFormation(new FangYuan(space, current_x, current_y));

        assertEquals(crops.get(0), space.getPos(current_x, current_y).getHolder());
        assertEquals(crops.get(0).getPosition(), space.getPos(current_x, current_y));

        assertEquals(crops.get(1), space.getPos(current_x-1, current_y+1).getHolder());
        assertEquals(crops.get(1).getPosition(), space.getPos(current_x-1, current_y+1));

        assertEquals(crops.get(2), space.getPos(current_x+1, current_y+1).getHolder());
        assertEquals(crops.get(2).getPosition(), space.getPos(current_x+1, current_y+1));

        assertEquals(crops.get(3), space.getPos(current_x-2, current_y+2).getHolder());
        assertEquals(crops.get(3).getPosition(), space.getPos(current_x-2, current_y+2));

        assertEquals(crops.get(4), space.getPos(current_x+2, current_y+2).getHolder());
        assertEquals(crops.get(4).getPosition(), space.getPos(current_x+2, current_y+2));

        assertEquals(crops.get(5), space.getPos(current_x-1, current_y+3).getHolder());
        assertEquals(crops.get(5).getPosition(), space.getPos(current_x-1, current_y+3));

        assertEquals(crops.get(6), space.getPos(current_x+1, current_y+3).getHolder());
        assertEquals(crops.get(6).getPosition(), space.getPos(current_x+1, current_y+3));
    }

    @Test
    public void test_setDongEFormation() throws Exception {
        crops.sort();
        int current_x = 4, current_y = 4;
        crops.setFormation(new DongE(space, current_x, current_y));

        assertEquals(crops.get(0), space.getPos(current_x, current_y).getHolder());
        assertEquals(crops.get(0).getPosition(), space.getPos(current_x, current_y));

        assertEquals(crops.get(1), space.getPos(current_x+1, current_y+1).getHolder());
        assertEquals(crops.get(1).getPosition(), space.getPos(current_x+1, current_y+1));

        assertEquals(crops.get(2), space.getPos(current_x, current_y+2).getHolder());
        assertEquals(crops.get(2).getPosition(), space.getPos(current_x, current_y+2));

        assertEquals(crops.get(3), space.getPos(current_x+1, current_y+3).getHolder());
        assertEquals(crops.get(3).getPosition(), space.getPos(current_x+1, current_y+3));

        assertEquals(crops.get(4), space.getPos(current_x, current_y+4).getHolder());
        assertEquals(crops.get(4).getPosition(), space.getPos(current_x, current_y+4));

        assertEquals(crops.get(5), space.getPos(current_x+1, current_y+5).getHolder());
        assertEquals(crops.get(5).getPosition(), space.getPos(current_x+1, current_y+5));

        assertEquals(crops.get(6), space.getPos(current_x, current_y+6).getHolder());
        assertEquals(crops.get(6).getPosition(), space.getPos(current_x, current_y+6));
    }
}