package nju.java;

import org.junit.Test;
import org.junit.Assert;

public class MyTest {

    @Test
    public void test0(){
        Field field = new Field();
        field.initWorld();
        Tile t = field.getTile(330,430);
        Assert.assertNotEquals(t, null);
    }

    @Test
    public void test(){
        Field field = new Field();
        field.initWorld();
        Huluwa c1 = new Huluwa(30,30, field, Huluwa.Color.红色, Huluwa.Order.老大);
        Huluwa c2 = new Huluwa(330,430, field, Huluwa.Color.橙色, Huluwa.Order.老二);
        int d = LittleUtils.distance(c1, c2);
        Assert.assertEquals(d, 500);
    }
}
