package util;

import archive.TimePoint;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ArchiveIOTest {
    @Test
    public void test_read() throws Exception {
        List<TimePoint> timePoints = ArchiveIO.read("sample.acv");
        TimePoint tp1 = timePoints.get(0);
        TimePoint tp2 = timePoints.get(1);

        assertEquals(State.FIGHT, tp1.creatures.get(0).state);
        assertEquals(7, tp1.creatures.get(0).x);
        assertEquals(13, tp1.creatures.get(0).y);

        assertEquals(State.ATTACK, tp1.creatures.get(1).state);
        assertEquals(7, tp1.creatures.get(1).x);
        assertEquals(6, tp1.creatures.get(1).y);

        assertEquals(State.DEAD, tp2.creatures.get(0).state);
        assertEquals(7, tp2.creatures.get(0).x);
        assertEquals(13, tp2.creatures.get(0).y);

        assertEquals(State.WAIT, tp2.creatures.get(1).state);
        assertEquals(7, tp2.creatures.get(1).x);
        assertEquals(6, tp2.creatures.get(1).y);
    }

    @Test
    public void test_write() throws Exception {
        List<TimePoint> timePoints = ArchiveIO.read("sample.acv");
        ArchiveIO.write(timePoints, "test.acv");
        List<TimePoint> timePoints2 = ArchiveIO.read("test.acv");

        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 2; j++) {
                assertEquals(timePoints.get(i).creatures.get(j).state, timePoints2.get(i).creatures.get(j).state);
                assertEquals(timePoints.get(i).creatures.get(j).x, timePoints2.get(i).creatures.get(j).x);
                assertEquals(timePoints.get(i).creatures.get(j).y, timePoints2.get(i).creatures.get(j).y);
            }
        }
    }
}