package util;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class Dom4jXmlReaderTest {
    @Test
    public void test_readDongE() throws Exception {
        ArrayList<Point> ap = Dom4jXmlReader.read("DongE.xml", true);
        assertEquals(0, ap.get(0).x);
        assertEquals(0, ap.get(0).y);

        assertEquals(1, ap.get(1).x);
        assertEquals(1, ap.get(1).y);

        assertEquals(0, ap.get(2).x);
        assertEquals(2, ap.get(2).y);

        assertEquals(1, ap.get(3).x);
        assertEquals(3, ap.get(3).y);

        assertEquals(0, ap.get(4).x);
        assertEquals(4, ap.get(4).y);

        assertEquals(1, ap.get(5).x);
        assertEquals(5, ap.get(5).y);

        assertEquals(0, ap.get(6).x);
        assertEquals(6, ap.get(6).y);
    }

    @Test
    public void test_readFangYuan() throws Exception {
        ArrayList<Point> ap = Dom4jXmlReader.read("FangYuan.xml", true);
        assertEquals(0, ap.get(0).x);
        assertEquals(0, ap.get(0).y);

        assertEquals(-1, ap.get(1).x);
        assertEquals(1, ap.get(1).y);

        assertEquals(1, ap.get(2).x);
        assertEquals(1, ap.get(2).y);

        assertEquals(-2, ap.get(3).x);
        assertEquals(2, ap.get(3).y);

        assertEquals(2, ap.get(4).x);
        assertEquals(2, ap.get(4).y);

        assertEquals(-1, ap.get(5).x);
        assertEquals(3, ap.get(5).y);

        assertEquals(1, ap.get(6).x);
        assertEquals(3, ap.get(6).y);
    }

    @Test
    public void test_readFengShi() throws Exception {
        ArrayList<Point> ap = Dom4jXmlReader.read("FengShi.xml", true);
        assertEquals(0, ap.get(0).x);
        assertEquals(0, ap.get(0).y);

        assertEquals(-1, ap.get(1).x);
        assertEquals(1, ap.get(1).y);

        assertEquals(0, ap.get(2).x);
        assertEquals(1, ap.get(2).y);

        assertEquals(1, ap.get(3).x);
        assertEquals(1, ap.get(3).y);

        assertEquals(0, ap.get(4).x);
        assertEquals(2, ap.get(4).y);

        assertEquals(0, ap.get(5).x);
        assertEquals(3, ap.get(5).y);

        assertEquals(0, ap.get(6).x);
        assertEquals(4, ap.get(6).y);
    }

    @Test
    public void test_readHeYi() throws Exception {
        ArrayList<Point> ap = Dom4jXmlReader.read("HeYi.xml", true);
        assertEquals(0, ap.get(0).x);
        assertEquals(0, ap.get(0).y);

        assertEquals(-1, ap.get(1).x);
        assertEquals(1, ap.get(1).y);

        assertEquals(1, ap.get(2).x);
        assertEquals(1, ap.get(2).y);

        assertEquals(-2, ap.get(3).x);
        assertEquals(2, ap.get(3).y);

        assertEquals(2, ap.get(4).x);
        assertEquals(2, ap.get(4).y);

        assertEquals(-3, ap.get(5).x);
        assertEquals(3, ap.get(5).y);

        assertEquals(3, ap.get(6).x);
        assertEquals(3, ap.get(6).y);
    }

    @Test
    public void test_readLongSnake() throws Exception {
        ArrayList<Point> ap = Dom4jXmlReader.read("LongSnake.xml", true);
        assertEquals(0, ap.get(0).x);
        assertEquals(0, ap.get(0).y);

        assertEquals(1, ap.get(1).x);
        assertEquals(0, ap.get(1).y);

        assertEquals(2, ap.get(2).x);
        assertEquals(0, ap.get(2).y);

        assertEquals(3, ap.get(3).x);
        assertEquals(0, ap.get(3).y);

        assertEquals(4, ap.get(4).x);
        assertEquals(0, ap.get(4).y);

        assertEquals(5, ap.get(5).x);
        assertEquals(0, ap.get(5).y);

        assertEquals(6, ap.get(6).x);
        assertEquals(0, ap.get(6).y);
    }
}